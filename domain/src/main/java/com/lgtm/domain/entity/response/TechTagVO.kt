package com.lgtm.domain.entity.response

data class TechTagVO(
    val name: String,
    val techTagId: Int,
    // todo : 서버에서 내려주는 이미지로 교체 할것
    val icon: String = "https://source.android.com/static/docs/setup/images/Android_symbol_green_RGB.png?hl=ko",
)