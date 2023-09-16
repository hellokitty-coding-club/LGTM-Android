package com.lgtm.android.common_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lgtm.android.common_ui.databinding.ItemDashboardParticipantBinding
import com.lgtm.android.common_ui.model.MemberMissionStatusUI
import com.lgtm.android.common_ui.util.ItemDiffCallback


class ParticipantAdapter : ListAdapter<MemberMissionStatusUI, ParticipantViewHolder>(
    ItemDiffCallback<MemberMissionStatusUI>(onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.memberId == new.memberId })
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDashboardParticipantBinding.inflate(layoutInflater, parent, false)
        return ParticipantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class ParticipantViewHolder(
    private val binding: ItemDashboardParticipantBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: MemberMissionStatusUI) {
        binding.data = item
    }
}