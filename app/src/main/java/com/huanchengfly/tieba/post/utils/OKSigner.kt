package com.huanchengfly.tieba.post.utils

import android.content.Context
import android.util.Log
import com.huanchengfly.tieba.post.api.TiebaApi
import com.huanchengfly.tieba.post.api.models.MSignBean
import com.huanchengfly.tieba.post.api.models.SignResultBean
import com.huanchengfly.tieba.post.api.retrofit.exception.getErrorCode
import com.huanchengfly.tieba.post.api.retrofit.exception.getErrorMessage
import com.huanchengfly.tieba.post.models.SignDataBean
import com.huanchengfly.tieba.post.models.database.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.lang.ref.WeakReference
import java.util.concurrent.ThreadLocalRandom
import kotlin.properties.Delegates

abstract class IOKSigner(
    context: Context
) {
    private val contextWeakReference: WeakReference<Context> = WeakReference(context)

    val context: Context
        get() = contextWeakReference.get()!!

    abstract suspend fun start(): Boolean

    fun signFlow(signDataBean: SignDataBean): Flow<SignResultBean> {
        return TiebaApi.getInstance()
            .signFlow(signDataBean.forumId, signDataBean.forumName, signDataBean.tbs)
    }

    fun getSignDelay(): Long {
        return if (context.appPreferences.oksignSlowMode) {
            ThreadLocalRandom.current().nextInt(3500, 8000).toLong()
        } else {
            2000
        }
    }
}

/*
class MultiAccountSigner(
        context: Context
) : IOKSigner(context) {
    private val accounts: MutableList<Account> = mutableListOf()

    override suspend fun start() {
        accounts.clear()
        accounts.addAll(AccountUtil.allAccounts)
    }

    interface ProgressListener {
        fun onStart(
                total: Int
        )

        fun onProgressStart(
                signDataBean: SignDataBean,
                current: Int,
                total: Int
        )

        fun onProgressFinish(
                signResultBean: SignResultBean,
                current: Int,
                total: Int
        )

        fun onFinish(
                success: Boolean,
                signedCount: Int,
                total: Int
        )

        fun onFailure(
                current: Int,
                total: Int,
                errorCode: Int,
                errorMsg: String
        )
    }
}
*/

class SingleAccountSigner(
    context: Context,
    private val account: Account
) : IOKSigner(context) {
    companion object {
        const val TAG = "SingleAccountSigner"
    }

    private val signData: MutableList<SignDataBean> = mutableListOf()
    private var position = 0
    private var successCount = 0
    private var totalCount = 0
    private var mSignCount = 0

    var lastFailure: Throwable? = null

    private var mProgressListener: ProgressListener? = null

    fun setProgressListener(listener: ProgressListener?): SingleAccountSigner {
        mProgressListener = listener
        return this
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun start(): Boolean {
        var result = false
        signData.clear()
        var userName by Delegates.notNull<String>()
        var tbs by Delegates.notNull<String>()
        Log.i(TAG, "start")

        AccountUtil.fetchAccountFlow(account).collect { acc ->
            userName = acc.name
            tbs = acc.tbs
        }

        // Collect ALL followed forums via paginated API
        val myUid = AccountUtil.getUid() ?: ""
        val allForums = mutableListOf<Pair<String, String>>()
        val seenIds = mutableSetOf<String>()
        var page = 1
        var hasMore = true
        val pageSize = 200

        while (hasMore) {
            try {
                val response = TiebaApi.getInstance()
                    .userLikeForum(uid = myUid, page = page).execute()
                val body = response.body()
                if (body != null) {
                    body.forumList?.forumList?.forEach { forum ->
                        if (seenIds.add(forum.id ?: "")) {
                            allForums.add((forum.id ?: "") to (forum.name ?: ""))
                        }
                    }
                    body.commonForumList?.forumList?.forEach { forum ->
                        if (seenIds.add(forum.id ?: "")) {
                            allForums.add((forum.id ?: "") to (forum.name ?: ""))
                        }
                    }
                    hasMore = body.hasMore == "1"
                } else {
                    hasMore = false
                }
            } catch (_: Exception) {
                hasMore = false
            }
            page++
            if (allForums.size >= pageSize * 15) hasMore = false // safety: max 3000
        }
        Log.i(TAG, "Collected ${allForums.size} forums for signing")

        if (allForums.isEmpty()) {
            mProgressListener?.onFinish(false, 0, 0)
            return false
        }

        // Build sign data
        allForums.forEach {
            signData.add(SignDataBean(it.second, it.first, userName, tbs, false))
        }
        totalCount = signData.size
        successCount = 0
        withContext(Dispatchers.Main) {
            mProgressListener?.onStart(totalCount)
        }

        // Sign each forum
        signData.asFlow()
            .onEach { data ->
                position = signData.indexOf(data)
                withContext(Dispatchers.Main) {
                    mProgressListener?.onProgressStart(data, position, totalCount)
                }
            }
            .flatMapConcat { signFlow(it) }
            .catch { e ->
                result = false
                lastFailure = e
                withContext(Dispatchers.Main) {
                    mProgressListener?.onFailure(
                        position, totalCount,
                        e.getErrorCode(), e.getErrorMessage()
                    )
                }
                delay(getSignDelay())
            }
            .onCompletion {
                withContext(Dispatchers.Main) {
                    mProgressListener?.onFinish(
                        successCount == totalCount,
                        successCount,
                        totalCount
                    )
                }
            }
            .collect {
                result = true
                successCount += 1
                withContext(Dispatchers.Main) {
                    mProgressListener?.onProgressFinish(
                        signData[position], it, position, totalCount
                    )
                }
                delay(getSignDelay())
            }

        return result
    }
}

interface ProgressListener {
    fun onStart(
        total: Int
    )

    fun onProgressStart(
        signDataBean: SignDataBean,
        current: Int,
        total: Int
    )

    fun onProgressFinish(
        signDataBean: SignDataBean,
        signResultBean: SignResultBean,
        current: Int,
        total: Int
    )

    fun onFinish(
        success: Boolean,
        signedCount: Int,
        total: Int
    )

    fun onFailure(
        current: Int,
        total: Int,
        errorCode: Int,
        errorMsg: String
    )
}