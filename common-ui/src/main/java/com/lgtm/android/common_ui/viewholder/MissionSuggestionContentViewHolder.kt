package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.databinding.ItemMissionSuggestionBinding
import com.lgtm.domain.mission_suggestion.SuggestionVO
import com.lgtm.domain.mission_suggestion.SuggestionContent
import com.lgtm.domain.util.dotStyleFormatter
import com.lgtm.domain.util.timeFormatter
import java.time.LocalDateTime

class MissionSuggestionContentViewHolder(
    private val binding: ItemMissionSuggestionBinding
): MissionSuggestionBaseViewHolder(binding) {
    override fun onBind(item: SuggestionContent) {
        item as SuggestionVO
        binding.data = item
        binding.btnLike.isLiked = item.isLiked
        binding.btnLike.likeNum = item.likeNum

        val localDateTime = LocalDateTime.parse(item.date)
        binding.createdDate = convertDateTimeToDotStyle(localDateTime)
        binding.createdTime = convertDateTimeToTime(localDateTime)
    }

    private fun convertDateTimeToDotStyle(date: LocalDateTime): String {
        return date.format(dotStyleFormatter)
    }

    private fun convertDateTimeToTime(date: LocalDateTime): String {
        return date.format(timeFormatter)
    }
}