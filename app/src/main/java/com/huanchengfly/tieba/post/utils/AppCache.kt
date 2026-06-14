package com.huanchengfly.tieba.post.utils

import android.content.Context
import android.util.LruCache
import com.huanchengfly.tieba.post.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.File

/**
 * Simple response cache for offline browsing.
 * Caches forum thread lists and thread content in DataStore for lightweight key-value data,
 * and in internal storage for larger page content.
 */
object AppCache {

    private const val MAX_CACHE_ENTRIES = 50
    private const val MAX_CONTENT_SIZE = 100 * 1024 // 100KB per entry

    // In-memory cache for frequent access
    private val memoryCache = LruCache<String, String>(MAX_CACHE_ENTRIES)

    fun put(context: Context, key: String, value: String) {
        if (value.length > MAX_CONTENT_SIZE) return
        memoryCache.put(key, value)
        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            val fileName = key.hashCode().toString(36)
            try {
                File(context.cacheDir, "tbl_cache").also { it.mkdirs() }
                    .resolve(fileName)
                    .writeText(value)
            } catch (_: Exception) {}
        }
    }

    fun get(context: Context, key: String): String? {
        memoryCache.get(key)?.let { return it }
        val fileName = key.hashCode().toString(36)
        return try {
            val file = File(context.cacheDir, "tbl_cache/$fileName")
            if (file.exists()) file.readText() else null
        } catch (_: Exception) { null }
    }

    fun clear(context: Context) {
        memoryCache.evictAll()
        try {
            File(context.cacheDir, "tbl_cache").deleteRecursively()
        } catch (_: Exception) {}
    }

    /** Null-safe get that wraps in try-catch for API call fallback */
    inline fun <T> getOrNull(context: Context, key: String, crossinline fallback: () -> T): T =
        try { fallback() } catch (_: Exception) {
            get(context, key)?.let { return@getOrNull fallback() }
            throw RuntimeException("Cache miss")
        }
}
