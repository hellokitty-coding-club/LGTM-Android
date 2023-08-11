package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemSduiEmptyBinding
import com.lgtm.domain.server_drive_ui.SduiContent
import com.lgtm.domain.server_drive_ui.SduiTheme
import com.lgtm.domain.server_drive_ui.SectionEmptyVO

class SduiEmptyViewHolder(
    private val binding: ItemSduiEmptyBinding
) : SduiBaseHolder(binding) {
    override fun bind(theme: SduiTheme, viewContent: SduiContent) {
        binding.theme = theme
        binding.data = viewContent as SectionEmptyVO
    }
}