package com.lgtm.android.main.notification

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.entity.response.NotificationVO
import com.lgtm.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationCenterViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository,
) : BaseViewModel() {

    private val _notificationList = MutableLiveData<List<NotificationVO>>()
    val notificationList: LiveData<List<NotificationVO>> = _notificationList
    fun getNotificationList() {
        viewModelScope.launch(lgtmErrorHandler) {
            notificationRepository.getNotificationList()
                .onSuccess {
                    _notificationList.postValue(it)
                }.onFailure {
                    Firebase.crashlytics.recordException(it)
                    Log.e(TAG, "getNotificationList: $it")
                }
        }
    }
}