package com.huanchengfly.tieba.post.ui.common.theme.interfaces

import android.content.Context
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

interface ThemeSwitcher {
    @ColorInt
    fun getColorByAttr(context: Context, @AttrRes attrId: Int): Int

    @ColorInt
    fun getColorById(context: Context, @ColorRes colorId: Int): Int
}
