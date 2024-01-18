package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemMissionSuggestionEmptyBinding
import com.lgtm.domain.mission_suggestion.SuggestionContent

class MissionSuggestionEmptyViewHolder (
    binding: ItemMissionSuggestionEmptyBinding
): MissionSuggestionBaseViewHolder(binding) {
    override fun onBind(item: SuggestionContent) {
        /* no-op */
    }
}