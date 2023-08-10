package com.lgtm.android.common_ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lgtm.android.common_ui.util.ItemDiffCallback
import com.lgtm.android.common_ui.viewholder.SduiBaseHolder
import com.lgtm.android.common_ui.viewholder.getViewHolder
import com.lgtm.domain.entity.response.SduiItemVO
import com.lgtm.domain.server_drive_ui.SduiViewType

class SduiAdapter : ListAdapter<SduiItemVO, SduiBaseHolder>(
    ItemDiffCallback<SduiItemVO>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.content == new.content }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SduiBaseHolder {
        return getViewHolder(parent, SduiViewType.getViewTypeByOrdinal(viewType))
    }

    override fun onBindViewHolder(holder: SduiBaseHolder, position: Int) {
        holder.bind(getItem(position).content)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.ordinal
    }
}

