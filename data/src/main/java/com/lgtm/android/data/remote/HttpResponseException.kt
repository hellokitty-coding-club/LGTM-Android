package com.lgtm.android.data.remote

// java.lang.Exception을 상속해서 내가 직접 만들어줘야하는 Exception 클래스
class HttpResponseException(
    val status: HttpResponseStatus,
    val httpCode: Int,
    val errorRequestUrl: String,
    msg: String,
    cause: Throwable?
) : Exception(msg, cause)