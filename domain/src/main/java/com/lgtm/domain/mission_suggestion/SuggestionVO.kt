package com.lgtm.domain.mission_suggestion

data class SuggestionVO(
    override val viewType: SuggestionViewType = SuggestionViewType.CONTENT,
    val title: String,
    val description: String,
    val suggestionId: Int,
    val date: String,
    val likeNum: String,
    val isLiked: Boolean,
    val isMyPost: Boolean
): SuggestionContent