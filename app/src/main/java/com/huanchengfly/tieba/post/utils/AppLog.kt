package com.huanchengfly.tieba.post.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object AppLog {
    data class Entry(
        val time: String = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date()),
        val tag: String,
        val message: String
    )

    private val buffer = mutableListOf<Entry>()

    @Synchronized
    fun d(tag: String, msg: String) {
        buffer.add(Entry(tag = tag, message = msg))
        if (buffer.size > 500) {
            buffer.removeAt(0)
        }
    }

    @Synchronized
    fun getLogs(): List<Entry> = buffer.toList()

    @Synchronized
    fun clear() = buffer.clear()
}
