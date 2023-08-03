package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.SignUpResponseVO

data class SignUpResponse(
    val memberId: Int?,
    val githubId: String?,
    val accessToken: String?,
    val refreshToken: String?
) {
    fun toVO(): SignUpResponseVO {
        return SignUpResponseVO(
            memberId = memberId ?: throw IllegalArgumentException("memberId is null"),
            githubId = githubId ?: throw IllegalArgumentException("githubId is null"),
            accessToken = accessToken ?: throw IllegalArgumentException("accessToken is null"),
            refreshToken = refreshToken ?: throw IllegalArgumentException("refreshToken is null")
        )
    }
}
