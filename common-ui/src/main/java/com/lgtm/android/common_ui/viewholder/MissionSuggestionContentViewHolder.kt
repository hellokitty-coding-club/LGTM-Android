package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemMissionSuggestionBinding
import com.lgtm.android.common_ui.model.SuggestionUI
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.domain.mission_suggestion.SuggestionContent

class MissionSuggestionContentViewHolder(
    private val binding: ItemMissionSuggestionBinding
): MissionSuggestionBaseViewHolder(binding) {
    private lateinit var navigateToSuggestionDetail: (Int) -> Unit

    override fun onBind(item: SuggestionContent) {
        item as SuggestionUI
        binding.data = item
        binding.clMissionSuggestion.setOnThrottleClickListener { navigateToSuggestionDetail(item.suggestionId) }
    }

    fun setNavigateToSuggestionDetail(navigateToSuggestionDetail: (Int) -> Unit) {
        this.navigateToSuggestionDetail = navigateToSuggestionDetail
    }
}