package com.lgtm.android.mission_detail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.entity.response.MissionDetailVO
import com.lgtm.domain.repository.MissionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionDetailViewModel @Inject constructor(
    private val missionRepository: MissionRepository
) : BaseViewModel() {

    private val _missionDetailVO: MutableLiveData<MissionDetailVO> = MutableLiveData()
    val missionDetailVO: LiveData<MissionDetailVO> = _missionDetailVO

    fun getMissionDetail(missionId: Int) {
        viewModelScope.launch(lgtmErrorHandler) {
            missionRepository.getMissionDetail(missionId)
                .onSuccess {
                    _missionDetailVO.value = it
                }.onFailure {
                    Log.e(TAG, "getMissionDetail: $it")
                }
        }
    }
}