package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemSduiItemBinding
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.server_drive_ui.SectionItemVO

class ProfileMissionViewHolder(
    private val binding: ItemSduiItemBinding
) : ProfileBaseHolder(binding) {

    private lateinit var navigateToMissionDetail: (Int) -> Unit
    fun setNavigateToMissionDetail(navigateToMissionDetail: (Int) -> Unit) {
        this.navigateToMissionDetail = navigateToMissionDetail
    }

    override fun bind(data: Profile) {
        binding.data = data as SectionItemVO
        binding.clMission.setOnThrottleClickListener {  navigateToMissionDetail(data.missionId) }
    }
}