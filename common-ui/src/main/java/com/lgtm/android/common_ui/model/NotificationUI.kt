package com.lgtm.android.common_ui.model

data class NotificationUI(
    val title: String,
    val body: String,
    val isRead: Boolean,
    val notificationId: Int,
    val date: String,
    val time: String,
)