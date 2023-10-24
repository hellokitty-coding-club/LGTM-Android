package com.lgtm.android.common_ui.util

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

sealed class ItemDecorationUtil {
    class BottomItemDecoration(@DimenRes private val padding: Int, private val index: Int) :
        RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val paddingInt = view.resources.getDimension(padding).toInt()

            when (parent.getChildAdapterPosition(view)) {
                index -> {
                    with(outRect) {
                        top = paddingInt
                    }
                }
            }
        }
    }
}
