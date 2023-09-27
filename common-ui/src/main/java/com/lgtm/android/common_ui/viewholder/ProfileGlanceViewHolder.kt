package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemProfileGlanceBinding
import com.lgtm.android.common_ui.model.mapper.toUiModel
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileGlance

class ProfileGlanceViewHolder(
    private val binding: ItemProfileGlanceBinding
) : ProfileBaseHolder(binding) {
    override fun bind(data: Profile) {
        val data : ProfileGlance = data as ProfileGlance
        binding.data = data.toUiModel()
    }
}