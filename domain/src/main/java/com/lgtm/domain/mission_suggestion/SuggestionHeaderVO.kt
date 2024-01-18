package com.lgtm.domain.mission_suggestion

data class SuggestionHeaderVO(
    override val viewType: SuggestionViewType = SuggestionViewType.HEADER,
    val title: String,
    val description: String
): SuggestionContent