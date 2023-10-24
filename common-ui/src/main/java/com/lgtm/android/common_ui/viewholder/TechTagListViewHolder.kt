package com.lgtm.android.common_ui.viewholder

import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.lgtm.android.common_ui.adapter.TechTagAdapter
import com.lgtm.android.common_ui.databinding.ItemTechTagListBinding
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.profileViewType.TechTagList

class TechTagListViewHolder(
    private val binding: ItemTechTagListBinding
) : ProfileBaseHolder(binding) {
    private lateinit var data: Profile
    private lateinit var techTagAdapter: TechTagAdapter
    override fun bind(data: Profile) {
        this.data = data
        initAdapter()
        setRecyclerViewLayoutManager()
        submitList()
    }

    private fun submitList() {
        val techTagList = (data as TechTagList).techTagList
        techTagAdapter.submitList(techTagList)
    }

    private fun initAdapter() {
        techTagAdapter = TechTagAdapter()
        binding.rvTechTag.adapter = techTagAdapter
    }

    private fun setRecyclerViewLayoutManager() {
        val layoutManager = FlexboxLayoutManager(binding.root.context).apply {
            flexWrap = FlexWrap.WRAP
        }
        binding.rvTechTag.layoutManager = layoutManager
    }

}