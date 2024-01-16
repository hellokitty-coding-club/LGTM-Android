package com.lgtm.domain.mission_suggestion

data class SuggestionHeaderVO(
    override val viewType: SuggestionViewType = SuggestionViewType.HEADER,
    override val title: String,
    override val description: String
): SuggestionContent