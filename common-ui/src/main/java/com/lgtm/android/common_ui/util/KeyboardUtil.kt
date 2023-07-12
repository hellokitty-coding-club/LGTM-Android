package com.lgtm.android.common_ui.util

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

class KeyboardUtil {

    fun hide(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        activity.currentFocus?.let { view ->
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            view.clearFocus()
        }
    }

    fun show(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        activity.currentFocus?.let { view ->
            inputMethodManager.showSoftInput(view, 0)
        }
    }

    fun setUpAsSoftKeyboard(view: View) {
        var screenMaxHeight = 0
        var keyboardHeight = 0
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val targetViewHeight = getTargetViewHeight(view)

            // screenMaxHeight, keyboardHeight 초기화
            if (targetViewHeight > screenMaxHeight) screenMaxHeight = targetViewHeight
            else keyboardHeight = screenMaxHeight - targetViewHeight

            // 키보드 표시 여부에 따라 margin 조정
            val param = view.layoutParams as ViewGroup.MarginLayoutParams
            param.bottomMargin = if (screenMaxHeight == targetViewHeight) 0 else keyboardHeight
            view.layoutParams = param
        }
    }

    private fun getTargetViewHeight(view: View): Int {
        val targetView = Rect()
        view.getWindowVisibleDisplayFrame(targetView)
        return targetView.bottom
    }
}
