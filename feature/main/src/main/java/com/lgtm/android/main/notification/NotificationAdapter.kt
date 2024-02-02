package com.lgtm.android.main.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lgtm.android.common_ui.model.NotificationUI
import com.lgtm.android.common_ui.util.ItemDiffCallback
import com.lgtm.android.main.databinding.ItemNotificationCenterBinding

class NotificationAdapter : ListAdapter<NotificationUI, NotificationViewHolder>(
    ItemDiffCallback<NotificationUI>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.notificationId == new.notificationId })
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNotificationCenterBinding.inflate(layoutInflater, parent, false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class NotificationViewHolder(
    private val binding: ItemNotificationCenterBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: NotificationUI) {
        binding.data = item
        binding.lgtmTimestamp.setTimeStamp(item.date, item.time)
    }
}