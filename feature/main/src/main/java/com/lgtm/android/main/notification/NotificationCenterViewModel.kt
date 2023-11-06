package com.lgtm.android.main.notification

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationCenterViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository,
) : BaseViewModel() {
    fun getNotificationList() {
        viewModelScope.launch(lgtmErrorHandler) {
            notificationRepository.getNotificationList()
                .onSuccess {
                    Log.d(TAG, "getNotificationList: $it")
                }.onFailure {
                    Log.e(TAG, "getNotificationList: $it")
                }
        }
    }
}