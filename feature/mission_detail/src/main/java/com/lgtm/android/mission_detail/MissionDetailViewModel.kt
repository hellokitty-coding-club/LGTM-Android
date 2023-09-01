package com.lgtm.android.mission_detail

import androidx.lifecycle.viewModelScope
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.repository.MissionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionDetailViewModel @Inject constructor(
    private val missionRepository: MissionRepository
) : BaseViewModel(){

    fun getMissionDetail(missionId: Int) {
        viewModelScope.launch(lgtmErrorHandler) {
            missionRepository.getMissionDetail(missionId)
        }
    }
}