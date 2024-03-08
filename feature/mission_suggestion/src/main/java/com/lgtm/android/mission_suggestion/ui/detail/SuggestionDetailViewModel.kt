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
import com.lgtm.domain.repository.AuthRepository
import com.lgtm.domain.repository.SuggestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionDetailViewModel @Inject constructor(
    private val suggestionRepository: SuggestionRepository,
    private val authRepository: AuthRepository
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

    /* 미션 제안 신고 기능 */
    fun getReportMessage() =
        "안녕하세요 고객님, LGTM 서비스 이용에 불편을 드려 죄송합니다. 보내주신 내용은 빠른 시일 내로 운영팀에서 처리하겠습니다.\n" +
                "처리 결과는 발신된 이메일 주소로 전달될 예정입니다. 감사합니다.\n\n" +
                "[신고 유형을 선택해주세요]\n" +
                "1. 성적, 폭력적 또는 혐오스러운 내용\n" +
                "2. 욕설 혹은 유해한 내용\n" +
                "3. 기타 부적절한 내용\n" +
                "4. 기타 서비스 이용 불편사항\n" +
                "응답 : \n" +
                "\n\n\n" +
                "[신고 내용]\n" +
                "불편을 겪으신 내용을 적어주세요. 필요시 사진을 첨부해주셔도 좋습니다.\n" +
                "응답 : \n" +
                "\n\n\n" +
                "-------------------------------------------------\n" +
                "아래 내용은 운영진에게 전달되는 정보니 수정하지 말아주세요:)\n\n" +
                "[신고 정보]\n" +
                "1. 신고자 ID : ${getUserId()}\n" +
                "2. 미션 제안 제목 : ${getMissionTitle()}\n" +
                "3. 미션 제안 본문 : ${getMissionDescription()} \n"

    private fun getUserId() = authRepository.getMemberId()

    private fun getMissionTitle(): String {
        detailState.value.apply {
            return if (this is UiState.Success) this.data.title else ""
        }
    }

    private fun getMissionDescription(): String {
        detailState.value.apply {
            return if (this is UiState.Success) this.data.description else ""
        }
    }
}