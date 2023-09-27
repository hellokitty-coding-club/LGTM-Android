package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemDetailTextBinding
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileDetailText

class DetailTextViewHolder(
    private val binding: ItemDetailTextBinding
) : ProfileBaseHolder(binding
) {
    override fun bind(profileInfo: Profile) {
        binding.data = (profileInfo as ProfileDetailText).detail
    }
}