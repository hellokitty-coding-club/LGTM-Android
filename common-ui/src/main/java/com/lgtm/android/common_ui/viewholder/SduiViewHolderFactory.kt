package com.lgtm.android.common_ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.databinding.ItemSduiCloserBinding
import com.lgtm.android.common_ui.databinding.ItemSduiEmptyBinding
import com.lgtm.android.common_ui.databinding.ItemSduiItemBinding
import com.lgtm.android.common_ui.databinding.ItemSduiSubItemBinding
import com.lgtm.android.common_ui.databinding.ItemSduiTitleBinding
import com.lgtm.android.common_ui.databinding.ItemSduiUnknownBinding
import com.lgtm.domain.server_drive_ui.SduiViewType

fun getSduiViewHolder(parent: ViewGroup, viewType: SduiViewType): SduiBaseHolder {

    val layout = getLayoutByViewType(viewType)
    val binding: ViewDataBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), layout, parent, false
    )

    return when (viewType) {
        SduiViewType.TITLE -> SduiTitleViewHolder(binding as ItemSduiTitleBinding)
        SduiViewType.ITEM -> SduiItemViewHolder(binding as ItemSduiItemBinding)
        SduiViewType.CLOSER -> SduiCloserViewHolder(binding as ItemSduiCloserBinding)
        SduiViewType.EMPTY -> SduiEmptyViewHolder(binding as ItemSduiEmptyBinding)
        SduiViewType.SUBITEM -> SduiSubItemViewHolder(binding as ItemSduiSubItemBinding)
        SduiViewType.UNKNOWN -> SduiUnknownViewHolder(binding as ItemSduiUnknownBinding)
    }
}

private fun getLayoutByViewType(viewType: SduiViewType): Int {
    return when (viewType) {
        SduiViewType.TITLE -> R.layout.item_sdui_title
        SduiViewType.ITEM -> R.layout.item_sdui_item
        SduiViewType.CLOSER -> R.layout.item_sdui_closer
        SduiViewType.EMPTY -> R.layout.item_sdui_empty
        SduiViewType.SUBITEM -> R.layout.item_sdui_sub_item
        SduiViewType.UNKNOWN -> R.layout.item_sdui_unknown
    }
}
