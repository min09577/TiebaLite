package com.huanchengfly.tieba.post.interfaces

import android.view.View

interface OnItemClickListener<T> {
    fun onClick(itemView: View, t: T, position: Int, viewType: Int)
}
