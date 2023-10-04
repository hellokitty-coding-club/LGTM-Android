package com.swm.logging.android

import com.google.gson.GsonBuilder
import com.swm.logging.android.logging_scheme.ExposureScheme
import com.swm.logging.android.logging_scheme.SWMLoggingScheme
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID
import java.util.concurrent.TimeUnit


object SWMLogging {

    private const val AUTHORIZATION = "Authorization"

    private lateinit var appVersion: String
    private lateinit var OSNameAndVersion: String
    private lateinit var baseUrl: String
    private lateinit var serverPath: String
    private lateinit var accessToken: String
    private lateinit var uuid: UUID
    private lateinit var loggingService: LoggingService
    private val observable = PublishSubject.create<SWMLoggingScheme>() // 발행
    private val observer = object : Observer<SWMLoggingScheme> { // 구독
        override fun onSubscribe(d: Disposable) {
            println("Rx: 구독 시작")
        }

        override fun onNext(value: SWMLoggingScheme) {
            // 각 아이템을 받을 때 호출됩니다.
            println("Rx: 아이템 받음: ${value.eventLogName}")
        }

        override fun onError(e: Throwable) {
            println("Rx: 에러 발생: ${e.message}")
        }

        override fun onComplete() {
            println("Rx: 스트림 완료")
        }
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    private val interceptor: Interceptor =
        Interceptor { chain ->
            with(chain) {
                proceed(
                    request().newBuilder().addHeader(AUTHORIZATION, accessToken).build()
                )
            }
        }

    private val loggingRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }


    suspend fun shotExposureLogging(exposureLogging: ExposureScheme): Response<BaseDTO> {
        observable.onNext(exposureLogging)
        checkInitialized()
        return loggingService.postLogging(serverPath, exposureLogging)
    }

    private fun checkInitialized() {
        check(::baseUrl.isInitialized) {
            "The 'baseUrl' property must be initialized. Initialize it by calling the 'init' method on the SwmLogging instance within your Application class."
        }
    }

    fun init(
        appVersion: String,
        osNameAndVersion: String,
        baseUrl: String,
        serverPath: String,
        token: String
    ) {
        this.appVersion = appVersion
        this.OSNameAndVersion = osNameAndVersion
        this.baseUrl = baseUrl
        this.serverPath = serverPath
        this.accessToken = token
        this.uuid = UUID.randomUUID()
        setLoggingService()
        observable.throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(observer)
    }

    private fun setLoggingService() {
        this.loggingService = loggingRetrofit.create(LoggingService::class.java)
    }

    fun getOsNameAndVersion(): String {
        return OSNameAndVersion
    }

}
