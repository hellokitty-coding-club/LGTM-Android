package com.lgtm.android.manage_mission.ping_pong_common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lgtm.android.common_ui.R.styleable
import com.lgtm.android.common_ui.R.styleable.PingPong_indicatingState
import com.lgtm.android.manage_mission.databinding.LayoutPingPongLineBinding
import com.lgtm.domain.constants.ProcessState
import com.lgtm.domain.constants.ProcessState.Companion.isPastThan
import com.lgtm.domain.constants.ProcessState.Companion.isSameWith

class PingPongLine @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutPingPongLineBinding by lazy {
        LayoutPingPongLineBinding.inflate(LayoutInflater.from(context), this, false)
    }

    private val indicatingState: ProcessState
    private lateinit var currentState: ProcessState

    init {
        context.theme.obtainStyledAttributes(attrs, styleable.PingPong, 0, 0).apply {
            try {
                val indicating = getEnum(PingPong_indicatingState, ProcessState.WAITING_FOR_PAYMENT)
                indicatingState = indicating
            } finally {
                recycle()
            }
        }
    }

    fun setCurrentState(state: ProcessState) {
        currentState = state
        setCircle()
    }

    private fun setCircle() {
        if (parent != null) {
            removeView(binding.root)
        }
        addView(binding.root)
        if (indicatingState.isPastThan(currentState) || indicatingState.isSameWith(currentState)) {
            binding.ivLinePast.visibility = VISIBLE
            binding.ivLineFuture.visibility = GONE
        } else {
            binding.ivLineFuture.visibility = VISIBLE
            binding.ivLinePast.visibility = GONE
        }
    }
}
