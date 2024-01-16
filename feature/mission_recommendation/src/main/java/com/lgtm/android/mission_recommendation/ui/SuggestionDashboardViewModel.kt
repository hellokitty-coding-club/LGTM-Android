package com.lgtm.android.mission_recommendation.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.entity.response.MissionSuggestionVO
import com.lgtm.domain.repository.SuggestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionDashboardViewModel @Inject constructor(
    private val suggestionRepository: SuggestionRepository
): BaseViewModel() {

    private val _missionSuggestion = MutableLiveData<MissionSuggestionVO>()
    val missionSuggestion: LiveData<MissionSuggestionVO> = _missionSuggestion

    fun getSuggestion() {
        viewModelScope.launch(lgtmErrorHandler) {
            suggestionRepository.getSuggestion()
                .onSuccess {
                    _missionSuggestion.postValue(it)
                    Log.d(TAG, "getSuggestion: $it")
                }.onFailure {
                    Firebase.crashlytics.recordException(it)
                    Log.e(TAG, "getSuggestion: $it")
                }
        }
    }

}