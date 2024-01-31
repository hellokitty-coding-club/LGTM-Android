package com.lgtm.android.data.datasource

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.MissionSuggestionDTO
import com.lgtm.android.data.model.response.SuggestionDTO
import com.lgtm.android.data.service.SuggestionService
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
}