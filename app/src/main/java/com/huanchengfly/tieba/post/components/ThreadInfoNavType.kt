package com.huanchengfly.tieba.post.components

import android.os.Bundle
import androidx.navigation.NavType
import com.huanchengfly.tieba.post.api.models.protos.ThreadInfo
import com.huanchengfly.tieba.post.api.urlDecode
import com.huanchengfly.tieba.post.api.urlEncode

/**
 * ThreadInfo 的 NavType — 替换 compose-destinations NavTypeSerializer
 * 通过 base64 序列化/反序列化 Protobuf ThreadInfo
 */
val ThreadInfoNavType = object : NavType<ThreadInfo>(
    isNullableAllowed = true
) {
    override fun get(bundle: Bundle, key: String): ThreadInfo? {
        return bundle.getString(key)?.let { fromString(it) }
    }

    override fun put(bundle: Bundle, key: String, value: ThreadInfo) {
        bundle.putString(key, toString(value))
    }

    override fun parseValue(value: String): ThreadInfo {
        return fromString(value)
    }

    override fun serializeAsValue(value: ThreadInfo): String {
        return toString(value)
    }

    private fun toString(value: ThreadInfo): String {
        return ThreadInfo.ADAPTER.encode(value).encodeBase64().urlEncode()
    }

    private fun fromString(str: String): ThreadInfo {
        return ThreadInfo.ADAPTER.decode(str.urlDecode().decodeBase64())
    }

    private fun ByteArray.encodeBase64(): String {
        return android.util.Base64.encodeToString(this, android.util.Base64.NO_WRAP)
    }

    private fun String.decodeBase64(): ByteArray {
        return android.util.Base64.decode(this, android.util.Base64.NO_WRAP)
    }
}
