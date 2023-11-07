package com.lgtm.android.main.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.response.SduiItemVO
import com.lgtm.domain.repository.AuthRepository
import com.lgtm.domain.repository.NotificationRepository
import com.lgtm.domain.usecase.MissionUseCase
import com.swm.logging.android.SWMLogging
import com.swm.logging.android.logging_scheme.SWMLoggingScheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: MissionUseCase,
    private val notificationRepository: NotificationRepository,
    authRepository: AuthRepository,
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


    private val _hasNewNotification = MutableLiveData<Boolean>()
    val hasNewNotification: LiveData<Boolean> = _hasNewNotification

    fun hasNewNotification() {
        viewModelScope.launch(lgtmErrorHandler) {
            notificationRepository.hasNewNotification()
                .onSuccess {
                    _hasNewNotification.postValue(it)
                }.onFailure {
                    Log.e(TAG, "hasNewNotification: ${it.message}")
                }
        }
    }

    fun shotHomeExposureLogging(swmLoggingScheme: SWMLoggingScheme) {
        SWMLogging.logEvent(swmLoggingScheme)
    }

    fun getUserRole() = role

}