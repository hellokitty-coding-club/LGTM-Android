package com.lgtm.android.common_ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lgtm.android.common_ui.util.ItemDiffCallback
import com.lgtm.android.common_ui.viewholder.MissionSuggestionBaseViewHolder
import com.lgtm.android.common_ui.viewholder.getSuggestionViewHolder
import com.lgtm.domain.mission_suggestion.SuggestionContent
import com.lgtm.domain.mission_suggestion.SuggestionViewType

class MissionSuggestionAdapter: ListAdapter<SuggestionContent, MissionSuggestionBaseViewHolder>(
    ItemDiffCallback<SuggestionContent>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionSuggestionBaseViewHolder {
        return getSuggestionViewHolder(parent, SuggestionViewType.getViewTypeByOrdinal(viewType))
    }

    override fun onBindViewHolder(holder: MissionSuggestionBaseViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.ordinal
    }
}