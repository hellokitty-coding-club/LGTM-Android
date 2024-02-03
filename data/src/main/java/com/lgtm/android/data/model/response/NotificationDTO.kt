package com.lgtm.android.data.model.response

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.lgtm.domain.entity.response.NotificationVO
import java.time.LocalDateTime
import java.time.format.DateTimeParseException

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
            date = parseDate()
        )
    }

    private fun parseDate(): LocalDateTime? {
        return try {
            checkNotNull(createdAt)
            LocalDateTime.parse(createdAt)
        } catch (e: DateTimeParseException) {
            Firebase.crashlytics.log("$e / Wrong Date format : $createdAt")
            null
        } catch (e: IllegalStateException) {
            Firebase.crashlytics.log("$e / created value might be null : $createdAt")
            null
        }
    }
}