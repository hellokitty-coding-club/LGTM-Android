package com.lgtm.domain.constants

enum class MissionDetailStatus {
    JUNIOR_PARTICIPATE_RECRUITING,
    JUNIOR_PARTICIPATE_MISSION_FINISH,
    JUNIOR_NOT_PARTICIPATE_RECRUITING,
    JUNIOR_NOT_PARTICIPATE_MISSION_FINISH,
    SENIOR_PARTICIPATE_RECRUITING,
    SENIOR_PARTICIPATE_MISSION_FINISH,
    SENIOR_NOT_PARTICIPATE_RECRUITING,
    SENIOR_NOT_PARTICIPATE_MISSION_FINISH;

    @Suppress("KotlinConstantConditions")
    companion object {
        fun getMissionDetailStatus(
            role: Role,
            isParticipating: Boolean,
            isRecruiting: Boolean
        ): MissionDetailStatus {
            return if (role == Role.REVIEWEE && isParticipating && isRecruiting) JUNIOR_PARTICIPATE_RECRUITING
            else if (role == Role.REVIEWEE && isParticipating && !isRecruiting) JUNIOR_PARTICIPATE_MISSION_FINISH
            else if (role == Role.REVIEWEE && !isParticipating && isRecruiting) JUNIOR_NOT_PARTICIPATE_RECRUITING
            else if (role == Role.REVIEWEE && !isParticipating && !isRecruiting) JUNIOR_NOT_PARTICIPATE_MISSION_FINISH
            else if (role == Role.REVIEWER && isParticipating && isRecruiting) SENIOR_PARTICIPATE_RECRUITING
            else if (role == Role.REVIEWER && isParticipating && !isRecruiting) SENIOR_PARTICIPATE_MISSION_FINISH
            else if (role == Role.REVIEWER && !isParticipating && isRecruiting) SENIOR_NOT_PARTICIPATE_RECRUITING
            else SENIOR_NOT_PARTICIPATE_MISSION_FINISH
        }
    }
}