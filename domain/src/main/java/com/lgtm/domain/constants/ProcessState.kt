package com.lgtm.domain.constants

enum class ProcessState {
    WAITING_FOR_PAYMENT,
    PAYMENT_CONFIRMATION,
    MISSION_PROCEEDING,
    CODE_REVIEW,
    MISSION_FINISHED,
    FEEDBACK_REVIEWED;

    companion object {
        fun getProcessState(processState: String?): ProcessState {
            requireNotNull(processState)
            values().forEach {
                if (it.name == processState) return it
            }
            throw IllegalStateException("ProcessState not found")
        }
    }
}