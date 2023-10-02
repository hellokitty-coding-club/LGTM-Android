package com.swm.logging.android

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object SwmLogging {

    private const val AUTHORIZATION = "Authorization"

    private lateinit var appVersion: String
    private lateinit var osName: String
    private lateinit var baseUrl: String
    private lateinit var accessToken: String

    private val interceptor: Interceptor =
        Interceptor { chain ->
            with(chain) {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader(AUTHORIZATION, accessToken)
                        .build()
                )
            }
        }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()


    lateinit var loggingRetrofit: Retrofit

    lateinit var loggingService: LoggingService

    //    var endpoint by Delegates.notNull<String>()
//    var header by Delegates.notNull<MutableMap<String, String>>()
//    var requestBody by Delegates´.notNull<MutableMap<String, Any>>()
//    fun setEndpoint(url: String) {
//        endpoint = url
//    }
//
//    // 1. Initial Time
//    fun setMandatoryFields(header: Map<String, String>?) {
//        header?.forEach { this.header[it.key] = it.value }
//    }


    lateinit var test: ExposureLogging

    suspend fun shotExposureLogging(exposureLogging: ExposureLogging): Response<BaseDTO> {
        test = exposureLogging
        //check(exposureLogging.getFieldSetStatus()) {"필수 로깅 필드가 누락되었습니다."}
        val loggingField = exposureLogging
        return loggingService.postExposureLogging(loggingField)
    }


    fun init(appVersion: String, osName: String, endpoint: String, token: String) {
        this.appVersion = appVersion
        this.osName = osName
        this.baseUrl = endpoint
        this.accessToken = token
        setRetrofit()
        setLoggingService()
    }

    private fun setRetrofit() {
        this.loggingRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .create()
                )
            ).build()
    }

    private fun setLoggingService() {
        this.loggingService = loggingRetrofit.create(LoggingService::class.java)
    }
}


abstract class ExposureLogging : SwmLoggingScheme()

abstract class ClickLogging : SwmLoggingScheme()

abstract class SwmLoggingScheme {
    open lateinit var eventLogName: String
    open lateinit var screenName: String
    open var logVersion: Int = 0
    private var logData: MutableMap<String, Any>? = mutableMapOf()
    fun setLoggingScheme(
        evenLogName: String,
        screenName: String,
        logVersion: Int,
        logData: MutableMap<String, Any>?
    ) {
        this.eventLogName = evenLogName
        this.screenName = screenName
        this.logVersion = logVersion
        this.logData = logData
    }
}
