package com.huanchengfly.tieba.post.ui.page

/**
 * 应用路由常量 — 替换 compose-destinations 自动生成的路由
 */
object Routes {
    // 根页面
    const val MAIN = "main"

    // 有 deepLink 的页面
    const val THREAD = "thread/{threadId}"                      // deepLink: tblite://thread/{threadId}
    const val FORUM = "forum/{forumName}"                        // deepLink: tblite://forum/{forumName}
    const val NOTIFICATIONS = "notifications/{initialTab}"       // deepLink: tblite://notifications/{initialTab}
    const val HISTORY = "history"                                // deepLink: tblite://history
    const val SEARCH = "search"                                  // deepLink: tblite://search
    const val THREAD_STORE = "favorite"                          // deepLink: tblite://favorite (favorite线程)

    // 详情页
    const val FORUM_DETAIL = "forum_detail/{forumId}?forumName={forumName}"
    const val FORUM_RULE = "forum_rule/{forumId}"
    const val FORUM_SEARCH_POST = "forum_search_post/{forumName}/{forumId}"
    const val USER_PROFILE = "user/{uid}"

    // 帖子子页面
    const val SUB_POSTS = "subposts/{threadId}?forumId={forumId}&postId={postId}&subPostId={subPostId}&anchor={anchor}"
    const val SUB_POSTS_SHEET = "subposts_sheet/{threadId}?forumId={forumId}&postId={postId}&subPostId={subPostId}&anchor={anchor}"  // BottomSheet
    const val REPLY = "reply/{threadId}?forumId={forumId}&forumName={forumName}&postId={postId}&subPostId={subPostId}&replyUserId={replyUserId}&replyUserName={replyUserName}&replyUserPortrait={replyUserPortrait}&tbs={tbs}"  // BottomSheet

    // 设置页
    const val SETTINGS = "settings"
    const val BLOCK_SETTINGS = "settings/block"
    const val BLOCK_LIST = "settings/block/list"
    const val ABOUT = "settings/about"
    const val HABIT = "settings/habit"
    const val MORE_SETTINGS = "settings/more"
    const val APP_THEME = "settings/theme"
    const val ACCOUNT = "settings/account"
    const val OKSIGN = "settings/oksign"
    const val CUSTOM = "settings/custom"

    // 其他
    const val WEBVIEW = "webview/{initialUrl}"
    const val LOGIN = "login"
    const val HOT_TOPIC_LIST = "hot_topic_list"
    const val HOT_PAGE = "hot"
    const val MONET_TEST = "monet_test"
    const val COPY_DIALOG = "copy_dialog/{text}"  // Dialog

    // 参数名常量
    object Args {
        const val THREAD_ID = "threadId"
        const val FORUM_ID = "forumId"
        const val FORUM_NAME = "forumName"
        const val POST_ID = "postId"
        const val SUB_POST_ID = "subPostId"
        const val SEE_LZ = "seeLz"
        const val SORT_TYPE = "sortType"
        const val FROM = "from"
        const val THREAD_INFO = "threadInfo"
        const val SCROLL_TO_REPLY = "scrollToReply"
        const val INITIAL_TAB = "initialTab"
        const val UID = "uid"
        const val TEXT = "text"
        const val INITIAL_URL = "initialUrl"
        const val REPLY_USER_ID = "replyUserId"
        const val REPLY_USER_NAME = "replyUserName"
        const val REPLY_USER_PORTRAIT = "replyUserPortrait"
        const val ANCHOR = "anchor"
        const val TBS = "tbs"
    }
}
