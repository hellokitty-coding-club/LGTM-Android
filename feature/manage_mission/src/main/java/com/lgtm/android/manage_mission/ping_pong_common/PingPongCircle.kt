package com.lgtm.android.manage_mission.ping_pong_common

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lgtm.android.common_ui.R.styleable
import com.lgtm.android.common_ui.R.styleable.PingPong_indicatingState
import com.lgtm.android.manage_mission.databinding.LayoutPingPongCircleBinding
import com.lgtm.domain.constants.ProcessState
import com.lgtm.domain.constants.ProcessState.Companion.isPast
import com.lgtm.domain.constants.ProcessState.Companion.isSame

class PingPongCircle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutPingPongCircleBinding by lazy {
        LayoutPingPongCircleBinding.inflate(LayoutInflater.from(context), this, false)
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
        addView(binding.root)
        if (indicatingState.isPast(currentState)) {
            binding.ivPast.visibility = VISIBLE
        } else if (indicatingState.isSame(currentState)) {
            binding.ivCurrent.visibility = VISIBLE
        } else {
            binding.ivFuture.visibility = VISIBLE
        }
    }
}

inline fun <reified T : Enum<T>> TypedArray.getEnum(index: Int, default: T) =
    getInt(index, -1).let {
        if (it >= 0) enumValues<T>()[it] else default
    }