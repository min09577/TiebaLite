package com.huanchengfly.tieba.post.models

import com.google.gson.annotations.SerializedName

open class ErrorBean : BaseBean() {
    @SerializedName("error_code")
    var errorCode: String? = null

    @SerializedName("error_msg")
    var errorMsg: String? = null
}
