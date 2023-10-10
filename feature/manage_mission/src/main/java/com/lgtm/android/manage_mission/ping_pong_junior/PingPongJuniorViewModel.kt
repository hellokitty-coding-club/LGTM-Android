package com.lgtm.android.manage_mission.ping_pong_junior

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.model.MissionHistoryUI
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

@HiltViewModel
class PingPongJuniorViewModel @Inject constructor(
    private val missionUseCase: MissionUseCase
) : BaseViewModel() {

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

    fun getMissionHistory(): MissionHistoryUI {
        return pingPongJuniorUI.value?.missionHistoryUI
            ?: throw IllegalArgumentException("missionHistory is null")
    }
}