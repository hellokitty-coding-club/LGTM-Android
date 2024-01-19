package com.lgtm.android.common_ui.util

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

sealed class ItemDecorationUtil {
    class BottomItemDecoration(@DimenRes private val padding: Int, private val index: Int) :
        RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State,
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

    class DividerItemDecoration(
        @DimenRes private val padding: Int,
        @ColorRes private val color: Int,
    ) :
        RecyclerView.ItemDecoration() {
        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val paddingInt = parent.resources.getDimension(padding)
            val height = parent.resources.getDimension(com.lgtm.android.common_ui.R.dimen.dp_1).toInt()
            val colorInt = parent.resources.getColor(color, null)
            val paint = android.graphics.Paint()
            paint.color = colorInt

            val left = parent.paddingStart + paddingInt
            val right = parent.width - parent.paddingEnd - paddingInt

            for (i in 0 until parent.childCount - 1) {
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams

                val top = (child.bottom + params.bottomMargin).toFloat()
                val bottom = top + height

                c.drawRect(left, top, right, bottom, paint)
            }

        }
    }

    class TopMarginItemDecoration(@DimenRes private val padding: Int): RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            val paddingInt = view.resources.getDimension(padding).toInt()
            outRect.top = paddingInt
        }
    }
}
