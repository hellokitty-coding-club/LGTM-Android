package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.CreateSuggestionResponseVO

data class CreateSuggestionResponseDTO(
    val writerId: Int?,
    val suggestionId: Int?
) {
    fun toVO() = CreateSuggestionResponseVO(
        writerId = writerId ?: 0,
        suggestionId = suggestionId ?: 0
    )
}