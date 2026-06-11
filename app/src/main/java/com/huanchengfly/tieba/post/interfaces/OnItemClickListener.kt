

package com.huanchengfly.tieba.post.interfaces;

import android.view.View;

interface OnItemClickListener<T> {
    fun onClick(View itemView, T t, int position, int viewType)
}

