package com.lgtm.domain.constants

enum class MissionStatus {
    RECRUITING,
    MISSION_PROCEEDING,
    MISSION_FINISHED;

    companion object {
        fun getMissionStatus(name: String?): MissionStatus {
            values().find { it.name == name }?.let {
                return it
            }?: throw IllegalArgumentException("Invalid Mission Status: $name")
        }
    }
}