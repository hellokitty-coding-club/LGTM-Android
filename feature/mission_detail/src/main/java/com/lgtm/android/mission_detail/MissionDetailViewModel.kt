package com.lgtm.android.mission_detail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.model.MissionDetailUiState
import com.lgtm.android.common_ui.model.mapper.toUiModel
import com.lgtm.domain.usecase.MissionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionDetailViewModel @Inject constructor(
    private val missionUseCase: MissionUseCase
) : BaseViewModel() {

    private val _missionDetailUiState: MutableLiveData<MissionDetailUiState> = MutableLiveData()
    val missionDetailUiState: LiveData<MissionDetailUiState> = _missionDetailUiState

    private val _recommendToEmptyVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    val recommendToEmptyVisibility: LiveData<Boolean> = _recommendToEmptyVisibility

    private val _notRecommendToEmptyVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    val notRecommendToEmptyVisibility: LiveData<Boolean> = _notRecommendToEmptyVisibility

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
                    _missionDetailUiState.postValue(it.toUiModel())
                }.onFailure {
                    Log.e(TAG, "getMissionDetail: $it")
                }
        }
    }
}