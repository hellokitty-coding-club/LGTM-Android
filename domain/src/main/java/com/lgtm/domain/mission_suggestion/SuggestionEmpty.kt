package com.lgtm.domain.mission_suggestion

data class SuggestionEmpty (
    override val viewType: SuggestionViewType = SuggestionViewType.EMPTY
): SuggestionContent