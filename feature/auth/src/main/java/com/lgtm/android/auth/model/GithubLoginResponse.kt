package com.lgtm.android.auth.model

import com.google.gson.annotations.SerializedName

data class GithubLoginResponse(
    val success: Boolean,
    val responseCode: Int,
    val message: String,
    @SerializedName("data")
    val memberData: MemberData?
)

