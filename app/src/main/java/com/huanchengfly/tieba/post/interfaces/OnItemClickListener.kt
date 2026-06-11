package com.huanchengfly.tieba.post.interfaces

import android.view.View

fun interface OnItemClickListener<T> {
    fun onClick(itemView: View, t: T, position: Int, viewType: Int)
}
