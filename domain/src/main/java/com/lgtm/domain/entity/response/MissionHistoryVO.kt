package com.lgtm.domain.entity.response

import java.time.LocalDateTime

data class MissionHistoryVO(
    val dateTime: LocalDateTime,
    val status: String
)