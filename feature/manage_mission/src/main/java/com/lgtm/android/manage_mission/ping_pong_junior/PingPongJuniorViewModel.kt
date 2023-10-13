package com.lgtm.android.manage_mission.ping_pong_junior

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.constant.InfoType
import com.lgtm.android.common_ui.model.EditTextData
import com.lgtm.android.common_ui.model.MissionProcessInfoUI
import com.lgtm.android.common_ui.model.PingPongJuniorUI
import com.lgtm.android.common_ui.model.mapper.toUiModel
import com.lgtm.android.common_ui.util.NetworkState
import com.lgtm.domain.constants.ProcessState
import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.response.PingPongJuniorVO
import com.lgtm.domain.usecase.MissionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class PingPongJuniorViewModel @Inject constructor(
    private val missionUseCase: MissionUseCase
) : BaseViewModel() {

    private var missionID by Delegates.notNull<Int>()

    private val _pingPongJuniorUI = MutableLiveData<PingPongJuniorUI>()
    val pingPongJuniorUI: LiveData<PingPongJuniorUI> = _pingPongJuniorUI

    private val _fetchMissionStatusState: MutableLiveData<NetworkState<PingPongJuniorVO>> =
        MutableLiveData(NetworkState.Init)
    val fetchMissionStatusState: LiveData<NetworkState<PingPongJuniorVO>> = _fetchMissionStatusState

    fun fetchJuniorMissionStatus(missionID: Int) {
        viewModelScope.launch {
            missionUseCase.fetchJuniorMissionStatus(missionID = missionID)
                .onSuccess {
                    _pingPongJuniorUI.postValue(it.toUiModel(getRole()))
                    _fetchMissionStatusState.postValue(NetworkState.Success(it))
                    Log.d(TAG, "fetchJuniorMissionStatus: $it")
                }.onFailure {
                    _fetchMissionStatusState.postValue(NetworkState.Failure(it.message))
                    Log.e(TAG, "fetchJuniorMissionStatus: $it")
                }
        }
    }

    fun getRole(): Role {
        return Role.REVIEWEE
    }

    fun getMissionStatus(): ProcessState {
        return pingPongJuniorUI.value?.processStatus
            ?: throw IllegalArgumentException("processStatus is null")
    }

    fun getMissionHistory(): MissionProcessInfoUI {
        return pingPongJuniorUI.value?.missionProcessInfoUI
            ?: throw IllegalArgumentException("missionHistory is null")
    }

    fun getAccountInfo(): String {
        return pingPongJuniorUI.value?.accountInfoUI?.accountInfo
            ?: throw IllegalArgumentException("accountInfo is null")
    }

    fun getAccountHolder(): String {
        return pingPongJuniorUI.value?.accountInfoUI?.sendTo
            ?: throw IllegalArgumentException("accountHolder is null")
    }

    private val _moveToNextProcessState: MutableLiveData<NetworkState<Boolean>> =
        MutableLiveData(NetworkState.Init)
    val moveToNextProcessState: LiveData<NetworkState<Boolean>> = _moveToNextProcessState
    fun confirmJuniorPayment() {
        viewModelScope.launch {
            missionUseCase.confirmJuniorPayment(missionID)
                .onSuccess {
                    _moveToNextProcessState.postValue(NetworkState.Success(it))
                    Log.d(TAG, "confirmJuniorPayment: $it")
                }.onFailure {
                    _moveToNextProcessState.postValue(NetworkState.Failure(it.message))
                    Log.e(TAG, "confirmJuniorPayment: $it")
                }
        }
    }

    fun setMissionId(missionID: Int) {
        this.missionID = missionID
    }


    val pullRequestUrlEditTextData = MutableLiveData(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            maxLength = 1000,
            hint = "Pull Request URL을 입력하세요."
        )
    )

    val pullRequestUrl: LiveData<String> =
        pullRequestUrlEditTextData.value?.text ?: MutableLiveData("")

    fun updateMissionTitleInfoStatus() {
        pullRequestUrlEditTextData.value?.infoStatus?.value =
            if (this.pullRequestUrl.value?.isEmpty() == true)
                InfoType.NONE
            else if (isGithubPrUrl(pullRequestUrl.value?.trim() ?: ""))
                InfoType.PROPER_URL
            else InfoType.GITHUB_URL_ONLY
    }

    private fun isGithubPrUrl(url: String): Boolean {
        return true
        val regex = Regex("(https://)?github.com/.*pull/\\d+")
        return regex.matches(url)
    }

    private val _isValidUrl = MutableLiveData(false)
    val isValidUrl: LiveData<Boolean> = _isValidUrl

    fun setIsValidUrl() {
        _isValidUrl.value =
            pullRequestUrlEditTextData.value?.infoStatus?.value == InfoType.PROPER_URL
                    && pullRequestUrlEditTextData.value?.text?.value?.isNotBlank() == true
    }

    fun requestCodeReview() {
        viewModelScope.launch {
            val pullRequestUrl = pullRequestUrl.value ?: return@launch
            missionUseCase.submitPullRequest(missionID, pullRequestUrl)
                .onSuccess {
                    _moveToNextProcessState.postValue(NetworkState.Success(it))
                }.onFailure {
                    _moveToNextProcessState.postValue(NetworkState.Failure(it.message))
                    Log.e(TAG, "requestCodeReview: $it")
                }
        }
    }
}