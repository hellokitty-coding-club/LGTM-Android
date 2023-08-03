package com.lgtm.android.data.model.response

data class BaseDTO<T>(
    val success: Boolean,
    val responseCode: Int,
    val message: String,
    val `data`: T
)

