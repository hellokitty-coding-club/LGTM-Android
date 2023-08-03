package com.lgtm.android.data.model.request

import com.google.gson.annotations.SerializedName

data class SignUpSeniorRequestDTO(
    val accountNumber: String,
    @SerializedName("agreeWithEventInfo")
    val isAgreeWithEventInfo: Boolean,
    val bankName: String,
    val careerPeriod: Int,
    val companyInfo: String,
    val deviceToken: String?,
    val githubId: String,
    val githubOauthId: Int,
    val introduction: String,
    val nickName: String,
    val position: String,
    val profileImageUrl: String,
    val tagList: List<String>
)