package com.lgtm.android.data.datasource

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.NotificationDTO
import com.lgtm.android.data.service.NotificationService
import javax.inject.Inject

class NotificationDatasource @Inject constructor(
    private val notificationService: NotificationService,
) : BaseNetworkDataSource() {
    suspend fun getNotificationList(): BaseDTO<ArrayList<NotificationDTO>> {
        return checkResponse(notificationService.getNotificationList())
    }
}