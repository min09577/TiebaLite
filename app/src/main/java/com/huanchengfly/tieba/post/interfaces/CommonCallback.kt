

package com.huanchengfly.tieba.post.interfaces;

interface CommonCallback<T> {
    fun onSuccess(T data)

    fun onFailure(int code, String error)
}
