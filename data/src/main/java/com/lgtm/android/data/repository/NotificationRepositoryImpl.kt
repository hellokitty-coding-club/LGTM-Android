package com.lgtm.android.data.repository

import com.lgtm.android.data.datasource.NotificationDatasource
import com.lgtm.domain.entity.response.NotificationVO
import com.lgtm.domain.repository.NotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDatasource : NotificationDatasource
) : NotificationRepository {
    override suspend fun getNotificationList() :Result<ArrayList<NotificationVO>>{
        return try {
            val response = notificationDatasource.getNotificationList()
            Result.success(response.data.map { it.toVO() } as ArrayList<NotificationVO>)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}