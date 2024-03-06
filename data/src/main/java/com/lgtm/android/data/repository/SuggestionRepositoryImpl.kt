package com.lgtm.android.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.lgtm.android.data.datasource.SuggestionDataSource
import com.lgtm.domain.entity.request.CreateSuggestionRequestVO
import com.lgtm.domain.entity.response.CreateSuggestionResponseVO
import com.lgtm.domain.entity.response.DeleteSuggestionVO
import com.lgtm.domain.entity.response.SuggestionLikeVO
import com.lgtm.domain.mission_suggestion.MissionSuggestionVO
import com.lgtm.domain.mission_suggestion.SuggestionVO
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

    override suspend fun getSuggestionDetail(suggestionId: Int): Result<SuggestionVO> {
        return try {
            val response = suggestionDataSource.getSuggestionDetail(suggestionId)
            Result.success(response.data.toVO())
        } catch (e: Exception) {
            Log.e(TAG, "getSuggestionDetail: ${this.javaClass} ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun createSuggestion(createSuggestionRequest: CreateSuggestionRequestVO): Result<CreateSuggestionResponseVO> {
        return try {
            val response = suggestionDataSource.createSuggestion(createSuggestionRequest)
            Result.success(response.data.toVO())
        } catch (e: Exception) {
            Log.e(TAG, "createSuggestion: ${this.javaClass} ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun likeSuggestion(suggestionId: Int): Result<SuggestionLikeVO> {
        return try {
            val response = suggestionDataSource.likeSuggestion(suggestionId)
            Result.success(response.data.toVO())
        } catch (e: Exception) {
            Log.e(TAG, "likeSuggestion: ${this.javaClass} ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun cancelLikeSuggestion(suggestionId: Int): Result<SuggestionLikeVO> {
        return try {
            val response = suggestionDataSource.cancelLikeSuggestion(suggestionId)
            Result.success(response.data.toVO())
        } catch (e: Exception) {
            Log.e(TAG, "cancelLikeSuggestion: ${this.javaClass} ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun deleteSuggestion(suggestionId: Int): Result<DeleteSuggestionVO> {
        return try {
            val response = suggestionDataSource.deleteSuggestion(suggestionId)
            Result.success(response.data.toVO())
        } catch (e: Exception) {
            Log.e(TAG, "deleteSuggestion: ${this.javaClass} ${e.message}")
            Result.failure(e)
        }
    }
}