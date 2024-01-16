package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemMissionSuggestionInfoBinding
import com.lgtm.domain.mission_suggestion.SuggestionContent
import com.lgtm.domain.mission_suggestion.SuggestionHeaderVO

class MissionSuggestionHeaderViewHolder(
    private val binding: ItemMissionSuggestionInfoBinding
): MissionSuggestionBaseViewHolder(binding) {
    override fun onBind(item: SuggestionContent) {
        binding.data = item as SuggestionHeaderVO
    }
}