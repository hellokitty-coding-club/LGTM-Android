package com.lgtm.android.common_ui.util

fun dpToPx(dp: Int, scale: Float): Int {
    return (dp * scale + 0.5f).toInt()
}