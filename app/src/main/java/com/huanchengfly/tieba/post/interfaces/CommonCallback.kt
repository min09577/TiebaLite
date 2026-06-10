

1|package com.huanchengfly.tieba.post.interfaces;
2|
3|interface CommonCallback<T> {
4|    fun onSuccess(T data)
5|
6|    fun onFailure(int code, String error)
7|}
