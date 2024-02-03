package com.lgtm.domain.entity.response

import java.time.LocalDateTime

data class NotificationVO(
    val title: String,
    val body: String,
    val isRead: Boolean,
    val notificationId: Int,
    val date: LocalDateTime?,
)