package com.lgtm.domain.entity.response

data class TechTagVO(
    val name: String,
    val techTagId: Int,
    // todo : 서버에서 내려주는 이미지로 교체 할것
    val icon: String = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSx3eMqWw8kN9Jy1NnWfsUd18PlY0cU6RWqBQ&usqp=CAU",
)