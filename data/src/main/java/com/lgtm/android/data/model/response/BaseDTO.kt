package com.lgtm.android.data.model.response

import com.google.gson.annotations.SerializedName

data class BaseDTO<T>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("errorCode")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val `data`: T
)

