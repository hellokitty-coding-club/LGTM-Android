package com.lgtm.android.suggestion_detail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.model.SuggestionUI
import com.lgtm.android.common_ui.model.mapper.toUiModel
import com.lgtm.android.common_ui.util.UiState
import com.lgtm.domain.repository.SuggestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionDetailViewModel @Inject constructor(
    private val suggestionRepository: SuggestionRepository
): BaseViewModel() {
    private val _detailState: MutableStateFlow<UiState<SuggestionUI>> = MutableStateFlow(
        UiState.Init)
    val detailState: StateFlow<UiState<SuggestionUI>>
        get() = _detailState

    fun fetchDetail(id: Int) {
        viewModelScope.launch(lgtmErrorHandler) {
            _detailState.value = UiState.Init

            suggestionRepository.getSuggestionDetail(id)
                .onSuccess {
                    _detailState.value = UiState.Success(
                        data = it.toUiModel()
                    )
                    Log.d(TAG, "getSuggestionDetail: $it")
                }.onFailure {
                    _detailState.value = UiState.Failure(
                        msg = it.message
                    )
                    Firebase.crashlytics.recordException(it)
                    Log.e(TAG, "getSuggestionDetail: $it")
                }
        }
    }
}