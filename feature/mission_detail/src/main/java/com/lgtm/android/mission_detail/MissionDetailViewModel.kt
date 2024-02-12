package com.lgtm.android.mission_detail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.model.MissionDetailUI
import com.lgtm.android.common_ui.model.mapper.toUiModel
import com.lgtm.android.common_ui.util.NetworkState
import com.lgtm.domain.constants.MissionDetailStatus
import com.lgtm.domain.entity.response.MissionDetailVO
import com.lgtm.domain.repository.AuthRepository
import com.lgtm.domain.usecase.MissionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionDetailViewModel @Inject constructor(
    private val missionUseCase: MissionUseCase,
    private val authRepository: AuthRepository,
) : BaseViewModel() {

    private val _missionDetailUiState: MutableLiveData<MissionDetailUI> = MutableLiveData()
    val missionDetailUiState: LiveData<MissionDetailUI> = _missionDetailUiState

    private val _missionDetailVO = MutableLiveData<MissionDetailVO>()

    private val _recommendToEmptyVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    val recommendToEmptyVisibility: LiveData<Boolean> = _recommendToEmptyVisibility

    private val _notRecommendToEmptyVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    val notRecommendToEmptyVisibility: LiveData<Boolean> = _notRecommendToEmptyVisibility

    private val _participateMissionUiState: MutableLiveData<NetworkState<Boolean>> =
        MutableLiveData(NetworkState.Init)
    val participateMissionUiState: LiveData<NetworkState<Boolean>> = _participateMissionUiState

    private val _deleteMissionState: MutableLiveData<NetworkState<Boolean>> =
        MutableLiveData(NetworkState.Init)
    val deleteMissionState: LiveData<NetworkState<Boolean>> = _deleteMissionState

    private val _missionDetailStatus: MutableLiveData<NetworkState<Boolean>> =
        MutableLiveData(NetworkState.Init)
    val missionDetailStatus: LiveData<NetworkState<Boolean>> = _missionDetailStatus

    fun isMyMission() =
        _missionDetailVO.value?.missionDetailStatus == MissionDetailStatus.SENIOR_PARTICIPATE_RECRUITING ||
                _missionDetailVO.value?.missionDetailStatus == MissionDetailStatus.SENIOR_PARTICIPATE_MISSION_FINISH

    fun getMissionDetailStatus(): MissionDetailStatus? = _missionDetailVO.value?.missionDetailStatus

    fun getMissionInfoMessage(): String {
        val missionTitle = _missionDetailUiState.value?.missionTitle ?: ""
        val reviewerNickname = _missionDetailUiState.value?.memberProfile?.nickname ?: ""
        return "\uD83C\uDF31LGTM\uD83C\uDF31\n\n\uD83D\uDCC4미션 제목 : $missionTitle\n🧑🏻‍💻리뷰어 : $reviewerNickname"
    }

    fun getMissionUrl(): String? {
        return missionDetailUiState.value?.missionRepositoryUrl
    }

    fun getReviewerId(): Int? {
        return missionDetailUiState.value?.memberProfile?.memberId
    }

    fun setRecommendToEmptyVisibility() {
        _recommendToEmptyVisibility.postValue(
            _missionDetailUiState.value?.recommendTo?.isBlank() ?: true
        )
    }

    fun setNotRecommendToEmptyVisibility() {
        _notRecommendToEmptyVisibility.postValue(
            _missionDetailUiState.value?.notRecommendTo?.isBlank() ?: true
        )
    }

    fun getMissionDetail(missionId: Int) {
        viewModelScope.launch(lgtmErrorHandler) {
            missionUseCase.getMissionDetail(missionId)
                .onSuccess {
                    _missionDetailVO.postValue(it)
                    _missionDetailUiState.postValue(it.toUiModel())
                    _missionDetailStatus.postValue(NetworkState.Success(true))
                }.onFailure {
                    Firebase.crashlytics.recordException(it)
                    _missionDetailStatus.postValue(NetworkState.Failure(it.message))
                    Log.e(TAG, "getMissionDetail: $it")
                }
        }
    }

    fun participateMission() {
        val missionId = _missionDetailVO.value?.missionId ?: return
        viewModelScope.launch(lgtmErrorHandler) {
            missionUseCase.participateMission(missionId)
                .onSuccess {
                    _participateMissionUiState.postValue(NetworkState.Success(it))
                }.onFailure {
                    Firebase.crashlytics.recordException(it)
                    _participateMissionUiState.postValue(NetworkState.Failure(it.message))
                }
        }
    }

    fun getMissionId() = _missionDetailVO.value?.missionId ?: -1
    fun deleteMission() {
        val missionId = _missionDetailVO.value?.missionId ?: return
        viewModelScope.launch(lgtmErrorHandler) {
            missionUseCase.deleteMission(missionId)
                .onSuccess {
                    _deleteMissionState.postValue(NetworkState.Success(it))
                }.onFailure {
                    Firebase.crashlytics.recordException(it)
                    _deleteMissionState.postValue(NetworkState.Failure(it.message))
                }
        }
    }


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
                "2. 미션 제목 : ${getMissionTitle()}\n" +
                "3. 미션 본문 : ${getMissionDescription()} \n" +
                "4. 미션 URL : ${getMissionUrl()}\n" +
                "5. 리뷰어 닉네임 : ${getReviewerNickname()}\n"

    private fun getUserId(): Int? {
        return authRepository.getMemberId()
    }

    private fun getMissionTitle(): String {
        return missionDetailUiState.value?.missionTitle ?: ""
    }

    private fun getMissionDescription(): String {
        return missionDetailUiState.value?.description ?: ""
    }

    private fun getReviewerNickname(): String {
        return missionDetailUiState.value?.memberProfile?.nickname ?: ""
    }

    fun getMissionDateAndTime(): Pair<String, String> {
        val missionDate = missionDetailUiState.value?.date ?: ""
        val missionTime = missionDetailUiState.value?.time ?: ""
        return missionDate to missionTime
    }
}