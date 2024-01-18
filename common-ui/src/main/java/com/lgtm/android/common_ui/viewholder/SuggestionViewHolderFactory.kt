package com.lgtm.android.common_ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.databinding.ItemMissionSuggestionBinding
import com.lgtm.android.common_ui.databinding.ItemMissionSuggestionEmptyBinding
import com.lgtm.android.common_ui.databinding.ItemMissionSuggestionInfoBinding
import com.lgtm.domain.mission_suggestion.SuggestionViewType

fun getSuggestionViewHolder(parent: ViewGroup, viewType: SuggestionViewType): MissionSuggestionBaseViewHolder {
    val layout = getLayoutByViewType(viewType)
    val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layout, parent, false)

    return when(viewType) {
        SuggestionViewType.CONTENT -> MissionSuggestionContentViewHolder(binding as ItemMissionSuggestionBinding)
        SuggestionViewType.HEADER -> MissionSuggestionHeaderViewHolder(binding as ItemMissionSuggestionInfoBinding)
        SuggestionViewType.EMPTY -> MissionSuggestionEmptyViewHolder(binding as ItemMissionSuggestionEmptyBinding)
    }

}

fun getLayoutByViewType(viewType: SuggestionViewType): Int {

    return when(viewType) {
        SuggestionViewType.CONTENT -> R.layout.item_mission_suggestion
        SuggestionViewType.HEADER -> R.layout.item_mission_suggestion_info
        SuggestionViewType.EMPTY -> R.layout.item_mission_suggestion_empty
    }

}