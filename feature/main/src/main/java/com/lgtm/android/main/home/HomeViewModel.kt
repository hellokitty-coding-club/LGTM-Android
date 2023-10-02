package com.lgtm.android.main.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.response.SduiItemVO
import com.lgtm.domain.logging.HomeScreenExposureLogging
import com.lgtm.domain.repository.AuthRepository
import com.lgtm.domain.usecase.MissionUseCase
import com.swm.logging.android.SwmLogging
import com.swm.logging.android.logging_scheme.ExposureLogging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: MissionUseCase,
    authRepository: AuthRepository
) : BaseViewModel() {

    private val role = authRepository.getMemberType()
    val fabVisibility = role == Role.REVIEWER

    private val _sduiList = MutableLiveData<List<SduiItemVO>>()
    val sduiList: LiveData<List<SduiItemVO>> = _sduiList

    fun getHomeInfo() {
        viewModelScope.launch(lgtmErrorHandler) {
            useCase.getHomeMission()
                .onSuccess {
                    _sduiList.postValue(it.contents)
                }.onFailure {
                    Log.e(TAG, "getHomeInfo: ${it.message}")
                }
        }
    }


    fun shotHomeExposureLogging() {
        viewModelScope.launch {
            val scheme = getHomeExposureLoggingScheme()
            SwmLogging.shotExposureLogging(scheme)
        }
    }

    private fun getHomeExposureLoggingScheme(): ExposureLogging {
        return HomeScreenExposureLogging.Builder()
            .setAge("3")
            .setTitleName("HomeCard")
            .build()
    }
}