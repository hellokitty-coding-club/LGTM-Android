package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.databinding.ItemDetailTextBinding
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileDetailText

class DetailTextViewHolder(
    private val binding: ItemDetailTextBinding
) : ProfileBaseHolder(
    binding
) {
    override fun bind(profileInfo: Profile) {
        val data = profileInfo as ProfileDetailText
        binding.data =
            if (data.isCareer) getCareerString(data.detail)
            else data.detail
    }

    private fun getCareerString(careerMonth: String): String {
        val careerMonthInt = careerMonth.toInt()
        val years = careerMonthInt / 12
        val months = careerMonthInt % 12

        val context = binding.root.context
        val yearString = context.getString(R.string.year)
        val monthString = context.getString(R.string.month)

        return if (months != 0) "$years$yearString $months$monthString"
        else "$years $yearString"
    }
}