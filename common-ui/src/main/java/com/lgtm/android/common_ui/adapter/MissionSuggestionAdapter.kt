package com.lgtm.android.common_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lgtm.android.common_ui.databinding.ItemMissionSuggestionBinding
import com.lgtm.android.common_ui.util.ItemDiffCallback
import com.lgtm.domain.entity.response.SuggestionVO
import com.lgtm.domain.util.dotStyleFormatter
import com.lgtm.domain.util.timeFormatter
import java.time.LocalDateTime

class MissionSuggestionAdapter: ListAdapter<SuggestionVO, MissionSuggestionViewHolder>(
    ItemDiffCallback<SuggestionVO>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.suggestionId == new.suggestionId }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionSuggestionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMissionSuggestionBinding.inflate(layoutInflater, parent, false)
        return MissionSuggestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MissionSuggestionViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class MissionSuggestionViewHolder(
    private val binding: ItemMissionSuggestionBinding
): RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: SuggestionVO) {
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