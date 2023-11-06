package com.lgtm.domain.entity.response

data class NotificationVO(
    val title: String,
    val body: String,
    val isRead: Boolean,
    val notificationId: Int,
)