package com.lgtm.domain.entity.response

data class MissionSuggestionVO(
    val infoTitle: String,
    val infoDescription: String,
    val suggestions: List<SuggestionVO>
)
