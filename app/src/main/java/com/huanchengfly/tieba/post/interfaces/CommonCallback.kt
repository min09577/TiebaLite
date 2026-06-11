package com.huanchengfly.tieba.post.interfaces

interface CommonCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(code: Int, error: String)
}
