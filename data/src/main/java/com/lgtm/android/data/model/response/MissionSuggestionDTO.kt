package com.lgtm.android.data.model.response

import com.lgtm.domain.mission_suggestion.MissionSuggestionVO

data class MissionSuggestionDTO(
    val infoTitle: String?,
    val infoDescription: String?,
    val suggestions: List<SuggestionDTO>?
) {
    fun toVO(): MissionSuggestionVO {
        return MissionSuggestionVO(
            infoTitle = requireNotNull(infoTitle),
            infoDescription = requireNotNull(infoDescription),
            suggestions = if (!suggestions.isNullOrEmpty()) suggestions.map { it.toVO() } else emptyList()
        )
    }
}