package com.lgtm.android.manage_mission.ping_pong_common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lgtm.android.common_ui.R.styleable
import com.lgtm.android.common_ui.R.styleable.PingPong_currentMissionState
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
    private val currentState: ProcessState

    init {
        context.theme.obtainStyledAttributes(attrs, styleable.PingPong, 0, 0).apply {
            try {
                val indicating = getString(PingPong_indicatingState).toString()
                val current = getString(PingPong_currentMissionState).toString()
                indicatingState = ProcessState.getProcessState(indicating)
                currentState = ProcessState.getProcessState(current)
                setCircle()
            } finally {
                recycle()
            }
        }
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