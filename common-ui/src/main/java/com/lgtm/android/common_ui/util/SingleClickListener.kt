package com.lgtm.android.common_ui.util

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


class OnThrottleClickListener(
    private val onClickListener: View.OnClickListener,
    private val interval: Long = 300L,
) : View.OnClickListener {

    private var clickable = true

    override fun onClick(v: View?) {
        if (clickable) {
            clickable = false
            v?.run {
                postDelayed({ clickable = true }, interval)
                onClickListener.onClick(v)
            }
        } else {
            Log.d(TAG, "onClick : operating setOnThrottleClickListener")
        }
    }
}

fun View.setOnThrottleClickListener(action: (v: View) -> Unit) {
    setOnClickListener(OnThrottleClickListener(action))
}

fun View.setOnThrottleClickListener(action: (v: View) -> Unit, interval: Long) {
    setOnClickListener(OnThrottleClickListener(action, interval))
}

@Composable
fun Modifier.throttleClickable(
    enabled: Boolean = false,
    onClick: () -> Unit
) = clickable(
    enabled = enabled,
    interactionSource = remember { MutableInteractionSource() },
    indication = null,
    onClick = onClick
)