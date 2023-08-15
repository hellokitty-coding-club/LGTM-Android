package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemSduiEmptyBinding
import com.lgtm.domain.server_drive_ui.SduiContent
import com.lgtm.domain.server_drive_ui.SduiEmptyUiState
import com.lgtm.domain.server_drive_ui.SduiTheme
import javax.inject.Inject

class SduiEmptyViewHolder @Inject constructor(
    private val binding: ItemSduiEmptyBinding,
) : SduiBaseHolder(binding) {

    override fun bind(theme: SduiTheme, viewContent: SduiContent) {
        check(viewContent is SduiEmptyUiState) { "viewContent must be SectionEmptyVO" }
        binding.theme = theme
        binding.uiState = viewContent
    }
}
