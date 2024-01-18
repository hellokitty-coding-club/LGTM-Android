package com.lgtm.android.common_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lgtm.android.common_ui.databinding.ItemMissionSuggestionBinding
import com.lgtm.android.common_ui.databinding.ItemMissionSuggestionInfoBinding
import com.lgtm.android.common_ui.util.ItemDiffCallback
import com.lgtm.android.common_ui.viewholder.MissionSuggestionBaseViewHolder
import com.lgtm.android.common_ui.viewholder.MissionSuggestionContentViewHolder
import com.lgtm.android.common_ui.viewholder.MissionSuggestionHeaderViewHolder
import com.lgtm.domain.mission_suggestion.SuggestionContent
import com.lgtm.domain.mission_suggestion.SuggestionViewType

class MissionSuggestionAdapter: ListAdapter<SuggestionContent, MissionSuggestionBaseViewHolder>(
    ItemDiffCallback<SuggestionContent>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.title == new.title }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionSuggestionBaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val suggestionViewType = SuggestionViewType.getViewTypeByOrdinal(viewType)
        val binding = when(suggestionViewType) {
            SuggestionViewType.CONTENT -> ItemMissionSuggestionBinding.inflate(layoutInflater, parent, false)
            SuggestionViewType.HEADER -> ItemMissionSuggestionInfoBinding.inflate(layoutInflater, parent, false)
        }
        return when(suggestionViewType) {
            SuggestionViewType.CONTENT -> MissionSuggestionContentViewHolder(binding as ItemMissionSuggestionBinding)
            SuggestionViewType.HEADER -> MissionSuggestionHeaderViewHolder(binding as ItemMissionSuggestionInfoBinding)
        }
    }

    override fun onBindViewHolder(holder: MissionSuggestionBaseViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.ordinal
    }
}