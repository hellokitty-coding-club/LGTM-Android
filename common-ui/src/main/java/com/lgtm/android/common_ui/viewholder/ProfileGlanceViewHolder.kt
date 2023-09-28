package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemProfileGlanceBinding
import com.lgtm.android.common_ui.model.mapper.toUiModel
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileGlance

class ProfileGlanceViewHolder(
    private val binding: ItemProfileGlanceBinding
) : ProfileBaseHolder(binding) {

    private lateinit var onClickGithubButton: () -> Unit

    fun setOnClickGithubButton(onClickGithubButton: () -> Unit) {
        this.onClickGithubButton = onClickGithubButton
    }

    override fun bind(data: Profile) {
        val profileGlanceData: ProfileGlance = data as ProfileGlance
        binding.data = profileGlanceData.toUiModel()
        binding.btnGithub.setOnClickListener { onClickGithubButton() }
    }
}