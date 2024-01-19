package com.lgtm.domain.mission_suggestion

data class MissionSuggestionVO(
    val infoTitle: String,
    val infoDescription: String,
    val suggestions: List<SuggestionContent>
)
