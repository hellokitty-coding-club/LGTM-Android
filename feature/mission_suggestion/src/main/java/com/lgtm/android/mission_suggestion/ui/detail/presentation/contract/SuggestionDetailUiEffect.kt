package com.lgtm.android.mission_suggestion.ui.detail.presentation.contract

sealed class SuggestionDetailUiEffect {
    data class SendEmail(val msg: String): SuggestionDetailUiEffect()
    object GoBack: SuggestionDetailUiEffect()
}