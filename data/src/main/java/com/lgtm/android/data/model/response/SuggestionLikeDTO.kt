package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.SuggestionLikeVO

data class SuggestionLikeDTO(
    val isLiked: Boolean?,
    val likeNum: String?
) {
    fun toVO() = SuggestionLikeVO(
        isLiked = requireNotNull(isLiked),
        likeNum = requireNotNull(likeNum)
    )
}
