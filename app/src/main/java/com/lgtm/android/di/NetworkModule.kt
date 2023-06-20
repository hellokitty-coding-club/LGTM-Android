package com.lgtm.android.di

import com.lgtm.android.BuildConfig.DEBUG
import com.lgtm.android.BuildConfig.LGTM_BASE_URL_DEBUG
import com.lgtm.android.BuildConfig.LGTM_BASE_URL_RELEASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesReadMeInterceptor(): Interceptor =
        Interceptor { chain ->
            with(chain) {
                proceed(
                    request()
                        .newBuilder() // add header (jwt) 생략
                        .build()
                )
            }
        }

    @Provides
    @Singleton
    fun providesReadMeOkHttpClient(
        interceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()


    @Provides
    @Singleton
    fun providesReadMeRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(if (DEBUG) LGTM_BASE_URL_DEBUG else LGTM_BASE_URL_RELEASE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}