package com.lgtm.android.common_ui.adapter

import android.view.View
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.constant.TechTagUI


@BindingAdapter("setIconColorRes")
fun MaterialButton.setIconColorRes(color: Int?) {
    if (color == 0 || color == null) return
    iconTint = ContextCompat.getColorStateList(this.context, color)
}

@BindingAdapter("setButtonStringRes")
fun AppCompatButton.setButtonStringRes(stringRes: Int?) {
    if (stringRes == 0 || stringRes == null) return
    text = this.context.getString(stringRes)
}

@BindingAdapter("setTextRes")
fun TextView.setTextRes(stringRes: Int?) {
    if (stringRes == 0 || stringRes == null) return
    text = this.context.getString(stringRes)
}

@BindingAdapter("infoStatusColor")
fun TextView.setInfoStatusColor(color: Int) {
    setTextColor(ContextCompat.getColor(this.context, color))
}

@BindingAdapter("setImageRes")
fun AppCompatImageView.setImageRes(icon: Int) {
    Glide.with(this.context)
        .load(icon)
        .into(this)
}

@BindingAdapter("setImageResOnButton")
fun MaterialButton.setImageResOnButton(@DrawableRes drawableRes: Int) {
    setIconResource(drawableRes)
}

@BindingAdapter("setImageUrl")
fun AppCompatImageView.setImageUrl(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}

@BindingAdapter("setPrUrlImage")
fun AppCompatImageView.setPrUrlImage(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(url)
            .error(R.drawable.ic_no_picture)
            .into(this)
    }
}

@BindingAdapter("setCircleProfileImageUrl")
fun AppCompatImageView.setCircleProfileImageUrl(url: String?) {
    Glide.with(this.context)
        .load(url)
        .error(R.drawable.img_profile_empty)
        .circleCrop()
        .into(this)
}

@BindingAdapter("setProfileImageUrl")
fun AppCompatImageView.setProfileImageUrl(url: String?) {
    Glide.with(this.context)
        .load(url)
        .error(R.drawable.img_profile_empty)
        .into(this)
}

@BindingAdapter("setTechTagImageByItsName")
fun AppCompatImageView.setTechTagImageByItsName(string: String) {
    val imageRes = TechTagUI.values().find { it.techTag.stack == string }?.defaultIcon
    imageRes?.let { setImageResource(it) }
}

@BindingAdapter("setRevieweeRoleImage")
fun AppCompatImageView.setRevieweeRoleImage(isSelected: Boolean) {
    if (isSelected) {
        setImageResource(R.drawable.ic_reviewee_white)
    } else {
        setImageResource(R.drawable.ic_reviewee_green)
    }
}

@BindingAdapter("setReviewerRoleImage")
fun AppCompatImageView.setReviewerRoleImage(isSelected: Boolean) {
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
        setBackgroundResource(R.drawable.rectangle_gray_8_radius_10)
    } else {
        setBackgroundResource(R.drawable.rectangle_border_gray2_radius_10)
    }
}

@BindingAdapter("setBookmarkBackground")
fun View.setBookmarkBackground(isBookmarked: Boolean) {
    if (isBookmarked) {
        setBackgroundResource(R.drawable.rectangle_black_radius_10)
    } else {
        setBackgroundResource(R.drawable.rectangle_white_stroke_gray_2_radius_10)
    }
}


@BindingAdapter("setBookmarkTextColor")
fun TextView.setBookmarkTextColor(isBookmarked: Boolean) {
    if (isBookmarked) {
        setTextColor(ContextCompat.getColor(this.context, R.color.white))
    } else {
        setTextColor(ContextCompat.getColor(this.context, R.color.black))
    }
}

@BindingAdapter("setBookmarkImage")
fun AppCompatImageView.setBookmarkImage(isBookmarked: Boolean) {
    if (isBookmarked) {
        setImageResource(R.drawable.ic_bookmark_selected)
    } else {
        setImageResource(R.drawable.ic_bookmark_unselected)
    }
}