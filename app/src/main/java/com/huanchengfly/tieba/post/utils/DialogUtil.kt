1|package com.huanchengfly.tieba.post.utils;
2|
3|import android.content.Context;
4|
5|import androidx.appcompat.app.AlertDialog;
6|
7|import com.huanchengfly.tieba.post.R;
8|
9|class DialogUtil {
10|    public static AlertDialog.Builder build(Context context) {
11|        return new AlertDialog.Builder(context);
12|    }
13|
14|    public static AlertDialog.Builder buildBottomDialog(Context context) {
15|        return new AlertDialog.Builder(context, R.style.Dialog_Bottom);
16|    }
17|}
