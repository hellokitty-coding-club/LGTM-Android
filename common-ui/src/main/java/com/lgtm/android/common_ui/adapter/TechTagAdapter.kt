package com.lgtm.android.common_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lgtm.android.common_ui.databinding.ItemTechTagBinding
import com.lgtm.android.common_ui.util.ItemDiffCallback
import com.lgtm.domain.entity.response.TechTagVO


class TechTagAdapter : ListAdapter<TechTagVO, TechTagViewHolder>(
    ItemDiffCallback<TechTagVO>(onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.techTagId == new.techTagId })
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechTagViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTechTagBinding.inflate(layoutInflater, parent, false)
        return TechTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TechTagViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class TechTagViewHolder(
    private val binding: ItemTechTagBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: TechTagVO) {
        binding.techTag = item
    }
}
