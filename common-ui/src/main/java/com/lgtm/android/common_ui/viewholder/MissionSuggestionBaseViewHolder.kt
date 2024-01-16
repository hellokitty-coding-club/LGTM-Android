package com.lgtm.android.common_ui.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lgtm.domain.mission_suggestion.SuggestionContent

abstract class MissionSuggestionBaseViewHolder(
    binding: ViewDataBinding
): RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(item: SuggestionContent)
}