package com.lgtm.android.common_ui.constant

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.lgtm.android.common_ui.R


enum class InfoType(
    @ColorInt
    val color: Int,
    val message: String,
    val isVisible: Boolean = true,
    @DrawableRes
    val icon: Int? = null
) {
    NONE(android.R.color.transparent, "", false),
    NO_SPACE(R.color.red, "띄어쓰기는 사용할 수 없습니다.", true, R.drawable.ic_info_red),
    SPACE_ONLY_NOT_ALLOWED(R.color.red, "띄어쓰기만 입력할 수 없습니다.", true, R.drawable.ic_info_red),
    VALID_REAL_NAME(R.color.red, "올바른 실명을 입력해 주세요.", true, R.drawable.ic_info_red),
    OVER_12_MONTHS_EXPERIENCE_REQUIRED(
        R.color.red,
        "경력 12개월 이상만 가입할 수 있습니다.",
        true,
        R.drawable.ic_info_red
    ),
    DUPLICATE_NICKNAME(R.color.red, "중복된 닉네임입니다.", true, R.drawable.ic_info_red),
    CAN_USE(R.color.blue, "사용 가능합니다.", true, R.drawable.ic_info_blue)
}

