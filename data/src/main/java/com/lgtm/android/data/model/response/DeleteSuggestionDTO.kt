package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.DeleteSuggestionVO

data class DeleteSuggestionDTO(
    val suggestionId: Int?,
    val success: Boolean?
) {
    fun toVO() = DeleteSuggestionVO(
        suggestionId = suggestionId ?: 0,
        success = success ?: false
    )
}