package com.lgtm.android.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.lgtm.android.data.datasource.SuggestionDataSource
import com.lgtm.domain.entity.response.MissionSuggestionVO
import com.lgtm.domain.repository.SuggestionRepository
import javax.inject.Inject

class SuggestionRepositoryImpl @Inject constructor(
    private val suggestionDataSource: SuggestionDataSource
): SuggestionRepository {
    override suspend fun getSuggestion(): Result<MissionSuggestionVO> {
        return try {
            val response = suggestionDataSource.getSuggestion()
            Result.success(response.data.toVO())
        } catch (e: Exception) {
            Log.e(TAG, "getSuggestion: ${this.javaClass} ${e.message}")
            Result.failure(e)
        }
    }
}