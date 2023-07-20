package com.lgtm.android.common_ui.base

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.lgtm.android.common_ui.R

@BindingAdapter("infoStatusColor")
fun TextView.setInfoStatusColor(color: Int) {
    setTextColor(ContextCompat.getColor(this.context, color))
}

@BindingAdapter("infoStatusIcon")
fun AppCompatImageView.setInfoStatusIcon(icon: Int) {
    setImageResource(icon)
}

@BindingAdapter("setRevieweeRoleImage")
fun AppCompatImageView.setImageBySelector(isSelected: Boolean) {
    if (isSelected) {
        setImageResource(R.drawable.ic_reviewee_white)
    } else {
        setImageResource(R.drawable.ic_reviewee_green)
    }
}

@BindingAdapter("setReviewerRoleImage")
fun AppCompatImageView.setImageBySelector2(isSelected: Boolean) {
    if (isSelected) {
        setImageResource(R.drawable.ic_reviewer_white)
    } else {
        setImageResource(R.drawable.ic_reviewer_green)
    }
}

@BindingAdapter("setRoleTextColor")
fun TextView.setTextColor(isSelected: Boolean) {
    if (isSelected) {
        setTextColor(ContextCompat.getColor(this.context, R.color.green))
    } else {
        setTextColor(ContextCompat.getColor(this.context, R.color.black))
    }
}

@BindingAdapter("setRoleBackground")
fun View.setBackgroundColor(isSelected: Boolean) {
    if (isSelected) {
        setBackgroundResource(R.drawable.rectangle_midnight_radius_10)
    } else {
        setBackgroundResource(R.drawable.rectangle_border_gray2_radius_10)
    }
}
