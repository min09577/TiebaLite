1|package com.huanchengfly.tieba.post.activities;
2|
3|import android.os.Bundle;
4|
5|import androidx.appcompat.app.AppCompatActivity;
6|
7|import com.huanchengfly.tieba.post.utils.TiebaUtil;
8|
9|class OKSignActivity : AppCompatActivity() {
10|    @Override
11|    protected fun onCreate(Bundle savedInstanceState) {
12|        super.onCreate(savedInstanceState);
13|        TiebaUtil.startSign(this);
14|        finish();
15|    }
16|}
17|
