package com.huanchengfly.tieba.post.api.models

import com.google.gson.annotations.SerializedName

data class OneKeySignInBean(
    @SerializedName("data")
    val data: SignInData?
) {
    data class SignInData(
        @SerializedName("signedForumAmount")
        val signedForumAmount: String,
        @SerializedName("unsignedForumAmount")
        val unsignedForumAmount: String
    )
}
