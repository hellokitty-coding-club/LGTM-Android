package com.swm.logging.android

data class BaseDTO(
    val success: Boolean,
    val responseCode: Int,
    val message: String,
    val data: String
)