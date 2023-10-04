package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.constant.ProfileTitleTextTypeUI
import com.lgtm.android.common_ui.databinding.ItemTitleTextBinding
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.profileViewType.ProfileTitleText

class TitleTextViewHolder(
    private val binding: ItemTitleTextBinding
) : ProfileBaseHolder(binding) {
    override fun bind(data: Profile) {
        val textType = (data as ProfileTitleText).profileTitleTextType
        val stringRes = ProfileTitleTextTypeUI.getStringRes(textType).stringRes
        binding.stringRes = stringRes
    }
}