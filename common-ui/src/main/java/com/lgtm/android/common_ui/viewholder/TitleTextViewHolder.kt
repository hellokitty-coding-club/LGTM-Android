package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemTitleTextBinding
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileTitleText

class TitleTextViewHolder(
    private val binding: ItemTitleTextBinding
) : ProfileBaseHolder(binding) {
    override fun bind(data: Profile) {
        binding.data = (data as ProfileTitleText).title
    }
}