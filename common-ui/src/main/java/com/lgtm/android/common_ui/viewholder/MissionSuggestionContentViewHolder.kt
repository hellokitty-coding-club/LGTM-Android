package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemMissionSuggestionBinding
import com.lgtm.android.common_ui.model.SuggestionUI
import com.lgtm.domain.mission_suggestion.SuggestionContent

class MissionSuggestionContentViewHolder(
    private val binding: ItemMissionSuggestionBinding
): MissionSuggestionBaseViewHolder(binding) {
    override fun onBind(item: SuggestionContent) {
        item as SuggestionUI
        binding.data = item
        binding.btnLike.isLiked = item.isLiked
        binding.btnLike.likeNum = item.likeNum
    }
}