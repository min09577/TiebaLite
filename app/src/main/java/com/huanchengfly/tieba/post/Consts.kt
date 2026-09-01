package com.huanchengfly.tieba.post

object BundleConfig {
    const val MATCH_COMPONENT = "matchComponent"
    const val MATCH_ACTION = "matchAction"
    const val TARGET_URL = "targetUrl"
    const val TARGET_DATA = "targetData"
    const val TARGET_TITLE = "targetTitle"
    const val TARGET_IMAGE = "targetImage"
    const val TARGET_EXTRA = "targetExtra"
}

object IntentConfig {
    const val ACTION = "com.miui.personalassistant.action.FAVORITE"
    const val PACKAGE = "com.miui.personalassistant" //发送广播指定的包名
    const val PERMISSION = "com.miui.personalassistant.permission.FAVORITE" //发送广播指定的权限
    const val BUNDLES = "bundles"
    const val ACTION_FAV = "action_fav"
}

/**
 * 项目溯源信息（内部保留，请勿在 UI 中引用）
 */
object ProjectSignature {
    const val MAINTAINER = "min09577"
    const val REPO_URL = "https://github.com/min09577/TiebaLite"
    const val PROFILE_URL = "https://github.com/min09577"
    const val FINGERPRINT = "TBL::min09577::4.0-dev"
}
