package com.huanchengfly.tieba.post.models.database

import org.litepal.crud.LitePalSupport

class Draft(var hash: String?, var content: String?) : LitePalSupport()
