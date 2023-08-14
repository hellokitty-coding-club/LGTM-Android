package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemSduiCloserBinding
import com.lgtm.domain.server_drive_ui.SduiContent
import com.lgtm.domain.server_drive_ui.SduiTheme
import com.lgtm.domain.server_drive_ui.SectionCloserVO

class SduiCloserViewHolder(
    private val binding: ItemSduiCloserBinding
) : SduiBaseHolder(binding) {
    override fun bind(theme: SduiTheme, viewContent: SduiContent) {
        binding.data = viewContent as SectionCloserVO
        binding.theme = theme
    }
}