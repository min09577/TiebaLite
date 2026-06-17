package com.huanchengfly.tieba.post.models.database

import org.litepal.crud.LitePalSupport

class Draft(
    var hash: String?,
    var content: String?,
    var threadId: Long = 0,
    var forumId: Long = 0,
    var forumName: String? = null,
    var postId: Long = 0,
    var createTime: Long = System.currentTimeMillis(),
    var contentPreview: String? = null,
) : LitePalSupport()
