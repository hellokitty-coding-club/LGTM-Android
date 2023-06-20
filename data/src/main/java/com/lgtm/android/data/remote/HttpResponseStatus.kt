package com.lgtm.android.data.remote

// 클라이언트에서 관리해줘야하는 Http 응답이 어떤게 있는지 관리하기위해 직접만든 HttpResponseStatus enum
enum class HttpResponseStatus {
    OK,
    BAD_REQUEST,
    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    INTERNAL_SERVER_ERROR;

    companion object {
        fun create(httpCode: Int): HttpResponseStatus {
            return when (httpCode) {
                200 -> OK
                400 -> BAD_REQUEST
                401 -> UNAUTHORIZED
                403 -> FORBIDDEN
                404 -> NOT_FOUND
                500 -> INTERNAL_SERVER_ERROR
                else -> throw IllegalArgumentException("Invalid HTTP code: $httpCode")
            }
        }
    }
}
