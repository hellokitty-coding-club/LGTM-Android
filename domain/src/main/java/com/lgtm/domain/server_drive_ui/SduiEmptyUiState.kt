package com.lgtm.domain.server_drive_ui

data class SduiEmptyUiState(
    val mainMessage: String,
    val subMessage: String,
    val isArrowVisible: Boolean = false,
    val arrowImage: Int? = null
)
