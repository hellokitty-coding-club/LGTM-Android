package com.lgtm.android.common_ui.base

import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import com.lgtm.android.common_ui.R

@BindingAdapter("app:btnBackground")
fun AppCompatButton.setBtnBackground(isEnabled: Boolean) {
    this.setBackgroundResource(
        if (isEnabled) R.drawable.rectangle_lgtm_black else R.drawable.rectangle_lgtm_gray
    )
}

@BindingAdapter("app:btnEnabled")
fun AppCompatButton.setBtnEnable(isEnabled: Boolean) {
    this.isEnabled = isEnabled
}