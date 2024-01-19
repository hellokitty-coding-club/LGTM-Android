package com.lgtm.android.common_ui.model

import com.lgtm.domain.mission_suggestion.SuggestionContent
import com.lgtm.domain.mission_suggestion.SuggestionViewType

data class SuggestionUI(
    override val viewType: SuggestionViewType,
    val title: String,
    val description: String,
    val suggestionId: Int,
    val date: String,
    val time: String,
    val likeNum: String,
    val isLiked: Boolean,
    val isMyPost: Boolean
): SuggestionContent