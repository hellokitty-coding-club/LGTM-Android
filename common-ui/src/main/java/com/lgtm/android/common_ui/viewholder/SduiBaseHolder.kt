package com.lgtm.android.common_ui.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lgtm.domain.server_drive_ui.SduiContent

abstract class SduiBaseHolder(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(viewContent: SduiContent)
}