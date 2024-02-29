package com.lgtm.android.common_ui.model

import android.text.SpannableString

data class NotificationUI(
    val title: String,
    val body: String,
    val isRead: Boolean,
    val notificationId: Int,
    val dateTime: SpannableString,
)