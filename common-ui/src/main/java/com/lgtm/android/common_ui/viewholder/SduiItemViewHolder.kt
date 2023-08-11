package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemSduiItemBinding
import com.lgtm.domain.server_drive_ui.SduiContent
import com.lgtm.domain.server_drive_ui.SduiTheme
import com.lgtm.domain.server_drive_ui.SectionItemVO

class SduiItemViewHolder(
    private val binding: ItemSduiItemBinding
) : SduiBaseHolder(binding) {
    override fun bind(theme: SduiTheme, viewContent: SduiContent) {
        binding.data = viewContent as SectionItemVO
        binding.theme = theme
    }
}