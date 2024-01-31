package com.lgtm.android.mission_recommendation.ui.detail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.model.mapper.toUiModel
import com.lgtm.android.mission_recommendation.ui.detail.presentation.SuggestionDetailState
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
    private val _detailState: MutableStateFlow<SuggestionDetailState> = MutableStateFlow(SuggestionDetailState.Loading)
    val detailState: StateFlow<SuggestionDetailState>
        get() = _detailState

    fun fetchDetail(id: Int) {
        viewModelScope.launch(lgtmErrorHandler) {
            _detailState.value = SuggestionDetailState.Loading

            suggestionRepository.getSuggestionDetail(id)
                .onSuccess {
                    _detailState.value = SuggestionDetailState.Main(
                        suggestionDetail = it.toUiModel()
                    )
                    Log.d(TAG, "getSuggestionDetail: $it")
                }.onFailure {
                    Firebase.crashlytics.recordException(it)
                    Log.e(TAG, "getSuggestionDetail: $it")
                }
        }
    }
}