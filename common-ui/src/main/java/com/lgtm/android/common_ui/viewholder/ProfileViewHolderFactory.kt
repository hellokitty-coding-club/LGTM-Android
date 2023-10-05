package com.lgtm.android.common_ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.databinding.ItemDetailTextBinding
import com.lgtm.android.common_ui.databinding.ItemProfileGlanceBinding
import com.lgtm.android.common_ui.databinding.ItemProfileImageBinding
import com.lgtm.android.common_ui.databinding.ItemSduiItemBinding
import com.lgtm.android.common_ui.databinding.ItemTechTagListBinding
import com.lgtm.android.common_ui.databinding.ItemThickDividerBinding
import com.lgtm.android.common_ui.databinding.ItemThinDividerBinding
import com.lgtm.android.common_ui.databinding.ItemTitleTextBinding
import com.lgtm.domain.profile.ProfileViewType

fun getProfileViewHolder(parent: ViewGroup, viewType: ProfileViewType): ProfileBaseHolder {

    val layout = getLayoutByViewType(viewType)
    val binding: ViewDataBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), layout, parent, false
    )

    return when (viewType) {
        ProfileViewType.BIG_PROFILE_IMAGE -> BigProfileViewHolder(binding as ItemProfileImageBinding)
        ProfileViewType.PROFILE_GLANCE -> ProfileGlanceViewHolder(binding as ItemProfileGlanceBinding)
        ProfileViewType.THICK_DIVIDER -> ThickDividerViewHolder(binding as ItemThickDividerBinding)
        ProfileViewType.TITLE_TEXT -> TitleTextViewHolder(binding as ItemTitleTextBinding)
        ProfileViewType.TECH_TAG_LIST -> TechTagListViewHolder(binding as ItemTechTagListBinding)
        ProfileViewType.THIN_DIVIDER -> ThinDividerViewHolder(binding as ItemThinDividerBinding)
        ProfileViewType.DETAIL_TEXT -> DetailTextViewHolder(binding as ItemDetailTextBinding)
        ProfileViewType.SECTION_ITEM_VO -> ProfileMissionViewHolder(binding as ItemSduiItemBinding)
    }
}

private fun getLayoutByViewType(viewType: ProfileViewType): Int {
    return when (viewType) {
        ProfileViewType.BIG_PROFILE_IMAGE -> R.layout.item_profile_image
        ProfileViewType.PROFILE_GLANCE -> R.layout.item_profile_glance
        ProfileViewType.THICK_DIVIDER -> R.layout.item_thick_divider
        ProfileViewType.TITLE_TEXT -> R.layout.item_title_text
        ProfileViewType.TECH_TAG_LIST -> R.layout.item_tech_tag_list
        ProfileViewType.THIN_DIVIDER -> R.layout.item_thin_divider
        ProfileViewType.DETAIL_TEXT -> R.layout.item_detail_text
        ProfileViewType.SECTION_ITEM_VO -> R.layout.item_sdui_item
    }

}
