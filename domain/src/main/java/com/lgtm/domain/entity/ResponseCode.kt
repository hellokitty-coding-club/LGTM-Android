package com.lgtm.domain.entity

enum class ResponseCode {
    OK,
    UNAVAILABLE_GITHUB_ACCESS_TOKEN,
    DUPLICATE_NICKNAME,
    INVALID_SKILL_TAG,
    INVALID_SKILL_PERIOD,
    INVALID_BANK_NAME,
    NON_EXISTENT_USER,
    INTERNAL_SERVER_ERROR;

    companion object {
        fun create(httpCode: Int): ResponseCode {
            return when (httpCode) {
                0 -> OK
                10003 -> UNAVAILABLE_GITHUB_ACCESS_TOKEN
                10004 -> DUPLICATE_NICKNAME
                10005 -> INVALID_SKILL_TAG
                10006 -> INVALID_SKILL_PERIOD
                10007 -> INVALID_BANK_NAME
                10100 -> NON_EXISTENT_USER
                else -> INTERNAL_SERVER_ERROR
            }
        }
    }
}
