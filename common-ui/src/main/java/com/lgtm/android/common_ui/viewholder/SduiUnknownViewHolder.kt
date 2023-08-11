package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemSduiUnknownBinding
import com.lgtm.domain.constants.Role
import com.lgtm.domain.server_drive_ui.SduiContent
import com.lgtm.domain.server_drive_ui.SduiTheme

class SduiUnknownViewHolder(
    private val binding: ItemSduiUnknownBinding
) : SduiBaseHolder(binding) {
    override fun bind(theme: SduiTheme, viewContent: SduiContent, role: Role?) {
        // no-op
    }
}