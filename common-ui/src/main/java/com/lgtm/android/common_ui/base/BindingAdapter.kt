package com.lgtm.android.common_ui.base

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("infoStatusColor")
fun TextView.setInfoStatusColor(color: Int) {
    setTextColor(ContextCompat.getColor(this.context, color))
}

@BindingAdapter("infoStatusIcon")
fun AppCompatImageView.setInfoStatusIcon(icon: Int) {
    setImageResource(icon)
}
