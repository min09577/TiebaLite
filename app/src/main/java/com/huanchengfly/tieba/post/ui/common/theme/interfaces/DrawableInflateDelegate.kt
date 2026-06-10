1|package com.huanchengfly.tieba.post.ui.common.theme.interfaces;
2|
3|import android.content.Context;
4|import android.graphics.drawable.Drawable;
5|import android.util.AttributeSet;
6|
7|import org.xmlpull.v1.XmlPullParser;
8|import org.xmlpull.v1.XmlPullParserException;
9|
10|import java.io.IOException;
11|
12|interface DrawableInflateDelegate<T extends Drawable> {
13|    T inflateDrawable(Context context, XmlPullParser parser, AttributeSet attrs) throws IOException, XmlPullParserException;
14|}
15|
