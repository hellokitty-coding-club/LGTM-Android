package com.lgtm.android.data.model.request

import com.google.gson.annotations.SerializedName

data class SignUpJuniorRequestDTO(
    val githubId: String,
    val githubOauthId: Int,
    @SerializedName("nickName")
    val nickname: String,
    val deviceToken: String?,
    val profileImageUrl: String,
    val introduction: String,
    val tagList: List<String>,
    val educationalHistory: String,
    val realName: String,
    @SerializedName("agreeWithEventInfo")
    val isAgreeWithEventInfo: Boolean
)