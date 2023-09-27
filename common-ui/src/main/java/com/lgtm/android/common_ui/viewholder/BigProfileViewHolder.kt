package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemProfileImageBinding
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileImage

class BigProfileViewHolder(
    private val binding: ItemProfileImageBinding
) : ProfileBaseHolder(binding) {
    override fun bind(data: Profile) {
        binding.data = data as ProfileImage
    }
}