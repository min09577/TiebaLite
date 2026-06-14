package com.huanchengfly.tieba.post.api.retrofit.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

/**
 * Lightweight rate limiter for baidu tieba API requests.
 * Ensures minimum interval between requests to the same endpoint.
 */
object RateLimitInterceptor : Interceptor {

    private val lastRequestMap = ConcurrentHashMap<String, Long>()
    private const val MIN_INTERVAL_MS = 200L

    override fun intercept(chain: Interceptor.Chain): Response {
        val host = chain.request().url.host
        val now = System.currentTimeMillis()
        val lastTime = lastRequestMap[host] ?: 0L
        val elapsed = now - lastTime
        if (elapsed < MIN_INTERVAL_MS) {
            Thread.sleep(MIN_INTERVAL_MS - elapsed)
        }
        lastRequestMap[host] = System.currentTimeMillis()
        return chain.proceed(chain.request())
    }
}
