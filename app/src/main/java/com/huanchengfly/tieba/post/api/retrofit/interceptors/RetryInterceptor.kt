package com.huanchengfly.tieba.post.api.retrofit.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.roundToLong

/**
 * Retries failed requests with exponential backoff.
 * Only retries on IOExceptions and 5xx server errors.
 */
object RetryInterceptor : Interceptor {

    private const val MAX_RETRIES = 2
    private const val BASE_DELAY_MS = 500L
    private const val MAX_DELAY_MS = 3000L

    override fun intercept(chain: Interceptor.Chain): Response {
        var retryCount = 0
        var lastException: IOException? = null

        while (retryCount <= MAX_RETRIES) {
            try {
                val response = chain.proceed(chain.request())
                if (response.isSuccessful || response.code < 500) {
                    return response
                }
                response.close()
            } catch (e: IOException) {
                lastException = e
            }

            retryCount++
            if (retryCount <= MAX_RETRIES) {
                val delay = min(
                    (BASE_DELAY_MS * 2.0.pow(retryCount - 1)).roundToLong(),
                    MAX_DELAY_MS
                )
                Thread.sleep(delay)
            }
        }

        throw lastException ?: IOException("Request failed after $MAX_RETRIES retries")
    }
}
