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
import com.lgtm.domain.usecase.MissionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionDetailViewModel @Inject constructor(
    private val missionUseCase: MissionUseCase
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
}