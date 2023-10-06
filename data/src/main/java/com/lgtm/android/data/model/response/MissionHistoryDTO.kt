package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.MissionHistoryVO
import java.time.LocalDateTime

data class MissionHistoryDTO(
    val dateTime: String?,
    val status: String?
) {
    fun toVO(): MissionHistoryVO {
        return MissionHistoryVO(
            dateTime = LocalDateTime.parse(dateTime)
                ?: throw NullPointerException("dateTime is null"),
            status = status ?: throw NullPointerException("status is null")
        )
    }
}