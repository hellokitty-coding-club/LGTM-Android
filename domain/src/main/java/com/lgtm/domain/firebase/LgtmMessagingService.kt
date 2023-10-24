package com.lgtm.domain.firebase

interface LgtmMessagingService {
    fun getDeviceToken(tokenCallBack: (String?) -> Unit)
}
