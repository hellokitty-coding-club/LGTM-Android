package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemSduiItemBinding
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.domain.server_drive_ui.SduiContent
import com.lgtm.domain.server_drive_ui.SduiTheme
import com.lgtm.domain.server_drive_ui.SectionItemVO

class SduiItemViewHolder(
    private val binding: ItemSduiItemBinding
) : SduiBaseHolder(binding) {

    private lateinit var navigateToMissionDetail : (SduiContent) -> Unit
    override fun bind(theme: SduiTheme, viewContent: SduiContent) {
        binding.data = viewContent as SectionItemVO
        binding.theme = theme
        binding.clMission.setOnThrottleClickListener { navigateToMissionDetail(viewContent) }

        if (viewContent.isScraped != null) {
            binding.bookmarkButton.isBookmarked = viewContent.isScraped == true
            binding.bookmarkButton.bookmarkNum = viewContent.scrapCount ?: 0
            binding.bookmarkButton.btnBookmark.setOnThrottleClickListener {
                // todo bookmark api 연동
            }
        }
    }

    fun setNavigateToMissionDetail(navigateToMissionDetail : (SduiContent) -> Unit) {
        this.navigateToMissionDetail = navigateToMissionDetail
    }
}