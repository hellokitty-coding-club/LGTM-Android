package com.lgtm.android.manage_mission.ping_pong_common

import android.content.res.TypedArray


inline fun <reified T : Enum<T>> TypedArray.getEnum(index: Int, default: T) =
    getInt(index, -1).let {
        if (it >= 0) enumValues<T>()[it] else default
    }