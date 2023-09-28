package com.lgtm.android.profile.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lgtm.android.common_ui.util.ItemDiffCallback
import com.lgtm.android.common_ui.viewholder.ProfileBaseHolder
import com.lgtm.android.common_ui.viewholder.ProfileGlanceViewHolder
import com.lgtm.android.common_ui.viewholder.ProfileMissionViewHolder
import com.lgtm.android.common_ui.viewholder.getProfileViewHolder
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileViewType
import com.lgtm.domain.profile.ProfileViewType.PROFILE_GLANCE
import com.lgtm.domain.profile.ProfileViewType.SECTION_ITEM_VO


class ProfileAdapter(
    private val onMissionClickListener: (Int) -> Unit,
    private val onGithubClickListener: () -> Unit
) : ListAdapter<Profile, ProfileBaseHolder>(
    ItemDiffCallback<Profile>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new })
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileBaseHolder {
        val viewHolder =
            getProfileViewHolder(parent, ProfileViewType.getViewTypeByOrdinal(viewType))
        when (viewType) {
            PROFILE_GLANCE.ordinal ->
                (viewHolder as? ProfileGlanceViewHolder)?.setOnClickGithubButton(onGithubClickListener)
            SECTION_ITEM_VO.ordinal ->
                (viewHolder as? ProfileMissionViewHolder)?.setNavigateToMissionDetail(onMissionClickListener)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ProfileBaseHolder, position: Int) {
        holder.bind(getItem(position))
        if (position == FIRST_MISSION_IDX)
            (holder as? ProfileMissionViewHolder)?.setPaddingTop()
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.ordinal
    }

    companion object {
        const val FIRST_MISSION_IDX = 10
    }
}

