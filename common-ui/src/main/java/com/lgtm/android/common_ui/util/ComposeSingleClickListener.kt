package com.lgtm.android.common_ui.util

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

class OnComposeThrottleClickListener(
    private val interval: Long = 300L
) {
    private val now: Long
        get() = System.currentTimeMillis()
    private var lastEventTime: Long = 0
    fun onClick(event: () -> Unit) {
        if (now - lastEventTime >= interval) {
            lastEventTime = now
            event()
        } else {
            Log.d(TAG, "onClick : operating composeThrottleClickListener")
        }

    }
}

@Composable
fun Modifier.throttleClickable(
    enabled: Boolean = false,
    onClick: () -> Unit
): Modifier {
    val throttleClickListener = remember { OnComposeThrottleClickListener() }

    return if (enabled) {
        clickable(
            onClick = { throttleClickListener.onClick { onClick() } },
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        )
    } else {
        this
    }
}