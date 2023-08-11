package com.lgtm.android.di

import com.google.gson.GsonBuilder
import com.lgtm.android.BuildConfig.DEBUG
import com.lgtm.android.BuildConfig.LGTM_BASE_URL_DEBUG
import com.lgtm.android.BuildConfig.LGTM_BASE_URL_RELEASE
import com.lgtm.android.data.datasource.LgtmPreferenceDataSource
import com.lgtm.android.data.datasource.LgtmPreferenceDataSource.Companion.PreferenceKey
import com.lgtm.android.data.deserializer.SduiViewTypeDeserializer
import com.lgtm.domain.entity.response.SduiItemVO
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

    private const val AUTHORIZATION = "Authorization"

    @Provides
    @Singleton
    fun providesLGTMInterceptor(
        lgtmPreferenceDataSource: LgtmPreferenceDataSource
    ): Interceptor =
        Interceptor { chain ->
            val accessToken = lgtmPreferenceDataSource.getValue(
                preferenceKey = PreferenceKey.ACCESS_TOKEN, defaultValue = "", isEncrypted = true
            )
            with(chain) {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader(AUTHORIZATION, accessToken)
                        .build()
                )
            }
        }

    @Provides
    @Singleton
    fun providesLGTMOkHttpClient(
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
    fun providesLGTMRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(if (DEBUG) LGTM_BASE_URL_DEBUG else LGTM_BASE_URL_RELEASE)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .registerTypeAdapter(SduiItemVO::class.java, SduiViewTypeDeserializer())
                        .create()
                )
            ).build()

}