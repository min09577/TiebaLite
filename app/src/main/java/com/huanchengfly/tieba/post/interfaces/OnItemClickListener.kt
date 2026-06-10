

1|package com.huanchengfly.tieba.post.interfaces;
2|
3|import android.view.View;
4|
5|interface OnItemClickListener<T> {
6|    fun onClick(View itemView, T t, int position, int viewType)
7|}
8|
