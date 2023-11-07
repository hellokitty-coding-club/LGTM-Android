package com.lgtm.android.common_ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lgtm.android.common_ui.util.ItemDiffCallback
import com.lgtm.android.common_ui.viewholder.SduiBaseHolder
import com.lgtm.android.common_ui.viewholder.SduiItemViewHolder
import com.lgtm.android.common_ui.viewholder.getSduiViewHolder
import com.lgtm.domain.entity.response.SduiItemVO
import com.lgtm.domain.server_drive_ui.SduiContent
import com.lgtm.domain.server_drive_ui.SduiViewType


class SduiAdapter(
    private val onMissionClickListener: (SduiContent) -> Unit
) : ListAdapter<SduiItemVO, SduiBaseHolder>(
    ItemDiffCallback<SduiItemVO>(onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.content == new.content })
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SduiBaseHolder {
        val viewHolder = getSduiViewHolder(parent, SduiViewType.getViewTypeByOrdinal(viewType))
        if (viewType == SduiViewType.ITEM.ordinal)
            return viewHolder.apply {
                (viewHolder as SduiItemViewHolder).setNavigateToMissionDetail(onMissionClickListener)
            }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SduiBaseHolder, position: Int) {
        holder.bind(
            theme = getItem(position).theme, viewContent = getItem(position).content
        )
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.ordinal
    }
}

