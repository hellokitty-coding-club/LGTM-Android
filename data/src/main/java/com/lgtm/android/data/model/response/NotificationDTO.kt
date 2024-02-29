package com.lgtm.android.data.model.response

import com.lgtm.android.data.model.mapper.parseDate
import com.lgtm.domain.entity.response.NotificationVO

data class NotificationDTO(
    val body: String?,
    val isRead: Boolean?,
    val notificationId: Int?,
    val title: String?,
    val createdAt: String?,
) {
    fun toVO(): NotificationVO {
        return NotificationVO(
            title = title.orEmpty(),
            body = body.orEmpty(),
            isRead = isRead ?: false,
            notificationId = requireNotNull(notificationId) { "notificationId is null" },
            dateTime = parseDate(createdAt)
        )
    }
}