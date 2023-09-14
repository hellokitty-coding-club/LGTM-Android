package com.lgtm.android.manage_mission

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.repository.MissionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val missionRepository: MissionRepository
) : BaseViewModel() {
    fun fetchDashboardInfo(missionId: Int) {
        viewModelScope.launch(lgtmErrorHandler) {
            missionRepository.fetchDashboardInfo(missionId)
                .onSuccess {
                    Log.d(TAG, "fetchMissionInfo: $it")
                }.onFailure {
                    Log.d(TAG, "fetchMissionInfo: $it")
                }
        }

    }
}