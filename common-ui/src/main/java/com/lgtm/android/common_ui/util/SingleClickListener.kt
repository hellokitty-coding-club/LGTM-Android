package com.lgtm.android.common_ui.util

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View

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