package com.lgtm.android.mission_suggestion.ui.detail.presentation.contract

interface SuggestionDetailInputs {
    fun deleteSuggestion()
    fun reportSuggestion()
    fun likeSuggestion()
    fun cancelLikeSuggestion()
    fun goBack()
}