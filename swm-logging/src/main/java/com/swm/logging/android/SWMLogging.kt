package com.swm.logging.android

import com.google.gson.GsonBuilder
import com.swm.logging.android.logging_scheme.SWMLoggingScheme
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID
import java.util.concurrent.TimeUnit


object SWMLogging {
    private lateinit var appVersion: String
    private lateinit var OSNameAndVersion: String
    private lateinit var deviceModel: String
    private lateinit var baseUrl: String
    private lateinit var serverPath: String
    private lateinit var userID: String
    private lateinit var uuid: UUID
    private lateinit var region: String
    private lateinit var loggingService: LoggingService

    private val observable = PublishSubject.create<SWMLoggingScheme>() // 발행
    private val observer = object : Observer<SWMLoggingScheme> { // 구독
        override fun onSubscribe(d: Disposable) {
            println("Rx: 구독 시작")
        }

        override fun onNext(value: SWMLoggingScheme) {
            println("Rx: 아이템 받음: ${value.eventLogName}")
            runBlocking {
                val result = async { shotLogging(value) }
                println("Rx: 로깅 결과: ${result.await()}")
            }
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
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    private val loggingRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    fun logEvent(swmLoggingScheme: SWMLoggingScheme) {
        observable.onNext(swmLoggingScheme)
    }

    private suspend fun shotLogging(swmLoggingScheme: SWMLoggingScheme): Response<BaseDTO> {
        checkInitialized()
        return loggingService.postLogging(serverPath, swmLoggingScheme)
    }

    private fun checkInitialized() {
        check(::baseUrl.isInitialized) {
            "The 'baseUrl' property must be initialized. Initialize it by calling the 'init' method on the SwmLogging instance within your Application class."
        }
    }

    fun init(
        appVersion: String,
        osNameAndVersion: String,
        deviceModel: String,
        baseUrl: String,
        serverPath: String,
        region: String,
        userID: String,
    ) {
        this.appVersion = appVersion
        this.OSNameAndVersion = osNameAndVersion
        this.deviceModel = deviceModel
        this.baseUrl = baseUrl
        this.serverPath = serverPath
        this.uuid = UUID.randomUUID()
        this.userID = userID
        this.region = region
        setLoggingService()
        observable.throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(observer)
    }

    private fun setLoggingService() {
        this.loggingService = loggingRetrofit.create(LoggingService::class.java)
    }

    fun getOsNameAndVersion(): String {
        return OSNameAndVersion
    }

    fun getUUID(): UUID = uuid
    fun getAppVersion(): String = appVersion
    fun getUserID(): String = userID

    /** 앱 실행시에는 로그아웃 상태였지만, 도중 로그아웃을 하는 유저에 한해 사용됨 */
    fun setUserId(userID: String) {
        this.userID = userID
    }
    fun getRegion(): String = region
    fun getDeviceModel(): String = deviceModel
}
