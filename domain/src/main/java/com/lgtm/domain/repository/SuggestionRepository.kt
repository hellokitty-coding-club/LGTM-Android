package com.lgtm.domain.repository

import com.lgtm.domain.entity.response.MissionSuggestionVO

interface SuggestionRepository {
    suspend fun getSuggestion(): Result<MissionSuggestionVO>
}