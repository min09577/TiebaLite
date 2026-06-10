1|package com.huanchengfly.tieba.post.models;
2|
3|import com.google.gson.annotations.SerializedName;
4|import com.huanchengfly.tieba.post.api.models.web.WebBaseBean;
5|
6|class MyInfoBean : WebBaseBean<MyInfoBean.MyInfoDataBean>() {
7|    public static class MyInfoDataBean {
8|        @SerializedName("itb_tbs")
9|        public String itbTbs;
10|        public String tbs;
11|        @SerializedName("portrait_url")
12|        public String avatarUrl;
13|        public long uid;
14|        @SerializedName("user_sex")
15|        public int userSex;
16|        @SerializedName("name_show")
17|        public String showName;
18|        public String intro;
19|        public String name;
20|        @SerializedName("concern_num")
21|        public String concernNum;
22|        @SerializedName("fans_num")
23|        public String fansNum;
24|        @SerializedName("like_forum_num")
25|        public String likeForumNum;
26|        @SerializedName("post_num")
27|        public String postNum;
28|        @SerializedName("is_login")
30|
33|        }
34|
35|        public MyInfoDataBean setIntro(String intro) {
36|            this.intro = intro;
38|        }
39|
42|        }
43|
46|        }
47|
50|        }
51|
54|        }
55|
58|        }
59|
62|        }
63|
66|        }
67|
70|        }
71|
74|        }
75|
78|        }
79|
82|        }
83|
86|        }
87|    }
88|}
