package com.lgtm.android.data.datasource

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.CreateSuggestionResponseDTO
import com.lgtm.android.data.model.response.DeleteSuggestionDTO
import com.lgtm.android.data.model.response.MissionSuggestionDTO
import com.lgtm.android.data.model.response.SuggestionDTO
import com.lgtm.android.data.model.response.SuggestionLikeDTO
import com.lgtm.android.data.service.SuggestionService
import com.lgtm.domain.entity.request.CreateSuggestionRequestVO
import javax.inject.Inject

class SuggestionDataSource @Inject constructor(
    private val suggestionService: SuggestionService
): BaseNetworkDataSource() {
    suspend fun getSuggestion(): BaseDTO<MissionSuggestionDTO> {
        return checkResponse(suggestionService.getSuggestion())
    }

    suspend fun getSuggestionDetail(suggestionId: Int): BaseDTO<SuggestionDTO> {
        return checkResponse(suggestionService.getSuggestionDetail(suggestionId))
    }

    suspend fun createSuggestion(createSuggestionRequest: CreateSuggestionRequestVO): BaseDTO<CreateSuggestionResponseDTO> {
        return checkResponse(suggestionService.createSuggestion(createSuggestionRequest))
    }

    suspend fun likeSuggestion(suggestionId: Int): BaseDTO<SuggestionLikeDTO> {
        return checkResponse(suggestionService.likeSuggestion(suggestionId))
    }

    suspend fun cancelLikeSuggestion(suggestionId: Int): BaseDTO<SuggestionLikeDTO> {
        return checkResponse(suggestionService.cancelLikeSuggestion(suggestionId))
    }

    suspend fun deleteSuggestion(suggestionId: Int): BaseDTO<DeleteSuggestionDTO> {
        return checkResponse(suggestionService.deleteSuggestion(suggestionId))
    }
}