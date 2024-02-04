package com.lgtm.domain.repository

import com.lgtm.domain.mission_suggestion.MissionSuggestionVO
import com.lgtm.domain.mission_suggestion.SuggestionVO

interface SuggestionRepository {
    suspend fun getSuggestion(): Result<MissionSuggestionVO>
    suspend fun getSuggestionDetail(suggestionId: Int): Result<SuggestionVO>
}