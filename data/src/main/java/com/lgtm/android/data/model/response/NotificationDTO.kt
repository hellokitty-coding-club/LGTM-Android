package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.NotificationVO

data class NotificationDTO(
    val body: String?,
    val isRead: Boolean?,
    val notificationId: Int?,
    val title: String?,
) {
    fun toVO(): NotificationVO {
        return NotificationVO(
            title = title ?: "",
            body = body ?: "",
            isRead = isRead ?: false,
            notificationId = notificationId ?: throw IllegalArgumentException("notificationId is null")
        )
    }
}