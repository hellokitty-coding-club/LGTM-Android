package com.lgtm.android.mission_recommendation.ui.detail.presentation

import com.lgtm.android.common_ui.model.SuggestionUI

sealed class SuggestionDetailState {
    object Loading: SuggestionDetailState()
    data class Main(
        val suggestionDetail: SuggestionUI
    ): SuggestionDetailState()
}