1|package com.huanchengfly.tieba.post.api.models.web;
2|
3|import com.google.gson.annotations.SerializedName;
4|
5|import java.util.List;
6|
7|class HotTopicThreadBean : WebBaseBean<HotTopicThreadBean.HotTopicThreadDataBean>() {
8|    public static class HotTopicThreadDataBean {
9|        @SerializedName("thread_list")
10|        private List<HotTopicMainBean.ThreadBean> threadList;
11|
12|        public List<HotTopicMainBean.ThreadBean> getThreadList() {
13|            return threadList;
14|        }
15|    }
16|}
17|
