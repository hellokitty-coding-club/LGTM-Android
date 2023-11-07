package com.lgtm.android.data.service

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.HasNotificationDTO
import com.lgtm.android.data.model.response.NotificationDTO
import retrofit2.Response
import retrofit2.http.GET

interface NotificationService {

    @GET("v1/notification")
    suspend fun getNotificationList(): Response<BaseDTO<List<NotificationDTO>>>

    @GET("v1/notification/new")
    suspend fun hasNewNotification(): Response<BaseDTO<HasNotificationDTO>>
}