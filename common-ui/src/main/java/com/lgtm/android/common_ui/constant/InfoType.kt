package com.lgtm.android.common_ui.constant

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.lgtm.android.common_ui.R


enum class InfoType(
    @ColorInt val color: Int,
    @StringRes val message: Int,
    val isVisible: Boolean = true,
    @DrawableRes val icon: Int? = null
) {
    NONE(android.R.color.transparent, R.string.empty_value, false),
    NO_SPACE(R.color.red, R.string.cannot_use_space, true, R.drawable.ic_info_red),
    SPACE_ONLY_NOT_ALLOWED(
        R.color.red,
        R.string.cannot_only_use_space,
        true,
        R.drawable.ic_info_red
    ),
    VALID_REAL_NAME(R.color.red, R.string.write_correct_name, true, R.drawable.ic_info_red),
    OVER_12_MONTHS_EXPERIENCE_REQUIRED(
        R.color.red, R.string.over_12_month_experience_required,
        true, R.drawable.ic_info_red
    ),
    GITHUB_URL_ONLY(R.color.red, R.string.github_url_only, true, R.drawable.ic_info_red),
    DUPLICATE_NICKNAME(R.color.red, R.string.duplicate_nickname, true, R.drawable.ic_info_red),
    CAN_USE(R.color.blue, R.string.can_use, true, R.drawable.ic_info_blue)
}

