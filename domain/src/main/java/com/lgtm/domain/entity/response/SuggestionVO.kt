package com.lgtm.domain.entity.response

data class SuggestionVO(
    val suggestionId: Int,
    val title: String,
    val description: String,
    val date: String,
    val likeNum: String,
    val isLiked: Boolean,
    val isMyPost: Boolean
)