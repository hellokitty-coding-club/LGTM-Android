package com.lgtm.android.common_ui.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.lgtm.domain.server_drive_ui.SduiTheme

@BindingAdapter("setTitleLayerBackground")
fun View.setTitleLayerBackground(theme: SduiTheme) {
    when (theme) {
        SduiTheme.LIGHT -> setBackgroundResource(com.lgtm.android.common_ui.R.drawable.rectangle_white_stroke_gray_2_radius_top_20)
        SduiTheme.DARK -> setBackgroundResource(com.lgtm.android.common_ui.R.drawable.rectangle_gray_2_stroke_gray_3_radius_top_20)
    }
}


@BindingAdapter("setItemLayerBackground")
fun View.setItemLayerBackground(theme: SduiTheme) {
    when (theme) {
        SduiTheme.LIGHT -> setBackgroundResource(com.lgtm.android.common_ui.R.drawable.rectangle_white_stroke_1_gray_2_side)
        SduiTheme.DARK -> setBackgroundResource(com.lgtm.android.common_ui.R.drawable.rectangle_gray_2_stroke_1_gray_3_side)
    }
}

@BindingAdapter("setCloserLayerBackground")
fun View.setCloserLayerBackground(theme: SduiTheme) {
    when (theme) {
        SduiTheme.LIGHT -> setBackgroundResource(com.lgtm.android.common_ui.R.drawable.rectangle_white_stroke_gray_2_radius_bottom_20)
        SduiTheme.DARK -> setBackgroundResource(com.lgtm.android.common_ui.R.drawable.rectangle_gray_2_stroke_gray_3_radius_bottom_20)
    }
}

@BindingAdapter("setDividerColor")
fun View.setDividerColor(theme: SduiTheme) {
    when (theme) {
        SduiTheme.LIGHT -> setBackgroundColor(
            ContextCompat.getColor(this.context, com.lgtm.android.common_ui.R.color.gray_2)
        )

        SduiTheme.DARK -> setBackgroundColor(
            ContextCompat.getColor(this.context, com.lgtm.android.common_ui.R.color.gray_3)
        )
    }
}


@BindingAdapter("setEmptyIcon")
fun AppCompatImageView.setEmptyImage(theme: SduiTheme) {
    when (theme) {
        SduiTheme.LIGHT -> setImageResource(com.lgtm.android.common_ui.R.drawable.img_home_empty_white)
        SduiTheme.DARK -> setImageResource(com.lgtm.android.common_ui.R.drawable.img_home_empty_gray)
    }
}