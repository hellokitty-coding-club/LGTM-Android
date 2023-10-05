package com.lgtm.android.common_ui.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lgtm.domain.profile.Profile

abstract class ProfileBaseHolder(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(data: Profile)
}
