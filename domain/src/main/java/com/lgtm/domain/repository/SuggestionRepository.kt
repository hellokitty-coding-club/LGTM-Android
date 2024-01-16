package com.lgtm.domain.repository

import com.lgtm.domain.mission_suggestion.MissionSuggestionVO

interface SuggestionRepository {
    suspend fun getSuggestion(): Result<MissionSuggestionVO>
}