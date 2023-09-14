package com.lgtm.android.manage_mission

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.entity.response.DashboardVO
import com.lgtm.domain.repository.MissionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val missionRepository: MissionRepository
) : BaseViewModel() {

    private val _dashboardInfo = MutableLiveData<DashboardVO>()
    val dashboardInfo: LiveData<DashboardVO> = _dashboardInfo
    fun fetchDashboardInfo(missionId: Int) {
        viewModelScope.launch(lgtmErrorHandler) {
            missionRepository.fetchDashboardInfo(missionId)
                .onSuccess {
                    _dashboardInfo.value = it
                    Log.d(TAG, "fetchMissionInfo: $it")
                }.onFailure {
                    Log.e(TAG, "fetchMissionInfo: $it")
                }
        }

    }
}