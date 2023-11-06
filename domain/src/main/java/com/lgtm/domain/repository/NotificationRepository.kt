package com.lgtm.domain.repository

import com.lgtm.domain.entity.response.NotificationVO

interface NotificationRepository {
    suspend fun getNotificationList(): Result<List<NotificationVO>>
}