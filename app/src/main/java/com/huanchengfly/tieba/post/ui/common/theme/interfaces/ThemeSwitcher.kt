1|package com.huanchengfly.tieba.post.ui.common.theme.interfaces;
2|
3|import android.content.Context;
4|
5|import androidx.annotation.AttrRes;
6|import androidx.annotation.ColorInt;
7|import androidx.annotation.ColorRes;
8|
9|interface ThemeSwitcher {
10|    @ColorInt
11|    int getColorByAttr(Context context, @AttrRes int attrId);
12|
13|    @ColorInt
14|    int getColorById(Context context, @ColorRes int colorId);
15|}
