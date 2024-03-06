package com.lgtm.android.mission_suggestion.ui.detail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.model.SuggestionUI
import com.lgtm.android.common_ui.model.mapper.toUiModel
import com.lgtm.android.common_ui.util.UiState
import com.lgtm.domain.entity.response.SuggestionLikeVO
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

    /* 미션 제안 상세 내용 */
    private val _detailState: MutableStateFlow<UiState<SuggestionUI>> = MutableStateFlow(
        UiState.Init)
    val detailState: StateFlow<UiState<SuggestionUI>>
        get() = _detailState

    private val _likeSuggestionState: MutableStateFlow<UiState<SuggestionLikeVO>> = MutableStateFlow(UiState.Init)
    val likeSuggestionState: StateFlow<UiState<SuggestionLikeVO>>
        get() = _likeSuggestionState

    fun fetchDetail(id: Int) {
        viewModelScope.launch(lgtmErrorHandler) {
            _detailState.value = UiState.Init

            suggestionRepository.getSuggestionDetail(id)
                .onSuccess {
                    _detailState.value = UiState.Success(data = it.toUiModel())
                    _likeSuggestionState.value = UiState.Success(data = createSuggestionLikeVO(it.isLiked, it.likeNum))
                    Log.d(TAG, "getSuggestionDetail: $it")
                }.onFailure {
                    _detailState.value = UiState.Failure(msg = it.message)
                    Firebase.crashlytics.recordException(it)
                    Log.e(TAG, "getSuggestionDetail: $it")
                }
        }
    }

    private fun createSuggestionLikeVO(isLiked: Boolean, likeNum: String) = SuggestionLikeVO(isLiked, likeNum)

    /* 미션 제안 좋아요 기능 */
    private fun getSuggestionId(): Int = if (detailState.value is UiState.Success) (detailState.value as UiState.Success<SuggestionUI>).data.suggestionId else -1

    fun likeSuggestion() {
        viewModelScope.launch(lgtmErrorHandler) {
            suggestionRepository.likeSuggestion(getSuggestionId())
                .onSuccess {
                    _likeSuggestionState.value = UiState.Success(data = it)
                    Log.d(TAG, "likeSuggestion: $it")
                }.onFailure {
                    _likeSuggestionState.value = UiState.Failure(msg = it.message)
                    Firebase.crashlytics.recordException(it)
                    Log.e(TAG, "likeSuggestion: $it")
                }
        }
    }

    fun cancelLikeSuggestion() {
        viewModelScope.launch(lgtmErrorHandler) {
            suggestionRepository.cancelLikeSuggestion(getSuggestionId())
                .onSuccess {
                    _likeSuggestionState.value = UiState.Success(data = it)
                    Log.d(TAG, "cancelLikeSuggestion: $it")
                }.onFailure {
                    _likeSuggestionState.value = UiState.Failure(msg = it.message)
                    Firebase.crashlytics.recordException(it)
                    Log.e(TAG, "cancelLikeSuggestion: $it")
                }
        }
    }

    /* 미션 제안 삭제 기능 */

    fun deleteSuggestion() {
        viewModelScope.launch(lgtmErrorHandler) {
            suggestionRepository.deleteSuggestion(getSuggestionId())
                .onSuccess {
                    Log.d(TAG, "deletedSuggestion: $it")
                }
                .onFailure {
                    Firebase.crashlytics.recordException(it)
                    Log.e(TAG, "deleteSuggestion: $it")
                }
        }
    }
}