package com.lgtm.domain.entity.response

import com.lgtm.domain.server_drive_ui.SduiContent
import com.lgtm.domain.server_drive_ui.SduiViewType

data class SduiItemVO(
    val viewType: SduiViewType,
    val content: SduiContent
)
