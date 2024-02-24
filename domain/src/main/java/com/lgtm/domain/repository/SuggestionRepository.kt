package com.lgtm.domain.repository

import com.lgtm.domain.entity.request.CreateSuggestionRequestVO
import com.lgtm.domain.entity.response.CreateSuggestionResponseVO
import com.lgtm.domain.mission_suggestion.MissionSuggestionVO
import com.lgtm.domain.mission_suggestion.SuggestionVO

interface SuggestionRepository {
    suspend fun getSuggestion(): Result<MissionSuggestionVO>
    suspend fun getSuggestionDetail(suggestionId: Int): Result<SuggestionVO>
    suspend fun createSuggestion(createSuggestionRequest: CreateSuggestionRequestVO): Result<CreateSuggestionResponseVO>
}