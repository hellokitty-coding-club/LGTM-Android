package com.lgtm.domain.usecase

import com.lgtm.domain.mission_suggestion.MissionSuggestionVO
import com.lgtm.domain.mission_suggestion.SuggestionContent
import com.lgtm.domain.mission_suggestion.SuggestionEmpty
import com.lgtm.domain.mission_suggestion.SuggestionHeaderVO
import com.lgtm.domain.repository.SuggestionRepository
import javax.inject.Inject

class SuggestionUseCase @Inject constructor(
    private val suggestionRepository: SuggestionRepository
) {

    suspend fun getSuggestionList(): Result<List<SuggestionContent>> {
        return try {
            val response = suggestionRepository.getSuggestion().getOrNull()
                ?: return Result.failure(Exception("response is null"))
            Result.success(createSuggestionContentList(response))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun createSuggestionContentList(response: MissionSuggestionVO): List<SuggestionContent> {
        val suggestionWithHeader: MutableList<SuggestionContent> = mutableListOf(
            SuggestionHeaderVO(
                title = response.infoTitle,
                description = response.infoDescription
            )
        )

        if (response.suggestions.isEmpty()) {
            suggestionWithHeader.add(getSuggestionEmptyView())
        } else {
            suggestionWithHeader.addAll(response.suggestions)
        }

        return suggestionWithHeader
    }

    private fun getSuggestionEmptyView(): SuggestionContent {
        return SuggestionEmpty()
    }

}