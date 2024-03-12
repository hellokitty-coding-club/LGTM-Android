package com.lgtm.android.mission_suggestion.ui.detail.presentation.contract

import com.lgtm.android.common_ui.model.SuggestionUI
import com.lgtm.android.common_ui.util.UiState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface SuggestionDetailOutputs {
    val detailState: StateFlow<UiState<SuggestionUI>>
    val detailUiEffect: SharedFlow<SuggestionDetailUiEffect>
}