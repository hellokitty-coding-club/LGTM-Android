package com.lgtm.android.data.model.request

import com.google.gson.annotations.SerializedName

data class SignUpJuniorRequestDTO(
    val githubId: String,
    val githubOauthId: Int,
    val nickName: String,
    val deviceToken: String?,
    val profileImageUrl: String,
    val introduction: String,
    val tagList: List<String>,
    val educationalHistory: String,
    val realName: String,
    @SerializedName("isAgreeWithEventInfo")
    val agreeWithEventInfo: Boolean
)