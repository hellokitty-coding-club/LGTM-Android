package com.lgtm.domain.entity

enum class ResponseCode {
    OK,
    DUPLICATE_NICKNAME,
    INVALID_SKILL_TAG,
    INVALID_SKILL_PERIOD,
    INVALID_BANK_NAME,
    NON_EXISTENT_USER;

    companion object {
        fun create(httpCode: Int): ResponseCode {
            return when (httpCode) {
                0 -> OK
                10004 -> DUPLICATE_NICKNAME
                10005 -> INVALID_SKILL_TAG
                10006 -> INVALID_SKILL_PERIOD
                10007 -> INVALID_BANK_NAME
                10100 -> NON_EXISTENT_USER
                else -> throw IllegalArgumentException("Invalid HTTP code: $httpCode")
            }
        }
    }
}
