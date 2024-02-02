package com.lgtm.android.common_ui.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lgtm.android.common_ui.databinding.LayoutLgtmTimestampBinding

class LGTMTimestamp @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutLgtmTimestampBinding by lazy {
        LayoutLgtmTimestampBinding.inflate(LayoutInflater.from(context), this, false)
    }

    fun setTimeStamp(date: String, time: String) {
        binding.tvDate.text = date
        binding.tvTime.text = time
    }

    init {
        addView(binding.root)
    }
}