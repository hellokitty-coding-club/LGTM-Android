package com.lgtm.domain.server_drive_ui

import com.lgtm.domain.constants.ArrowDirection


data class SduiEmptyUiState(
    val mainMessage: String,
    val subMessage: String,
    val isArrowVisible: Boolean = false,
    val arrowType: ArrowDirection? = null
) : SduiContent
