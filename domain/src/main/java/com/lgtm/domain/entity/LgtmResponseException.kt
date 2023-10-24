package com.lgtm.domain.entity

class LgtmResponseException(
    val responseCode: ResponseCode,
    val httpCode: Int,
    override val message: String,
    override val cause: Throwable?
) : Exception(message, cause)