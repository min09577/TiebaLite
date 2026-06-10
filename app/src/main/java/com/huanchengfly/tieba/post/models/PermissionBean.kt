1|package com.huanchengfly.tieba.post.models;
2|
3|import androidx.annotation.DrawableRes;
4|
5|class PermissionBean {
    var 6|    id: Int = 0
    var 7|    data: String? = null
    var 8|    title: String? = null
    var 9|    icon: Int = 0
10|
11|    public PermissionBean(int id, String data, String title, @DrawableRes int icon) {
12|        this.id = id;
13|        this.data = data;
14|        this.title = title;
15|        this.icon = icon;
16|    }
17|
20|    }
21|
22|    public PermissionBean setId(int id) {
23|        this.id = id;
25|    }
26|
29|    }
30|
31|    public PermissionBean setData(String data) {
32|        this.data = data;
34|    }
35|
38|    }
39|
40|    public PermissionBean setTitle(String title) {
41|        this.title = title;
43|    }
44|
47|    }
48|
49|    public PermissionBean setIcon(@DrawableRes int icon) {
50|        this.icon = icon;
52|    }
53|}
54|
