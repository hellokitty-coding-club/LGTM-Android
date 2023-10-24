package com.lgtm.domain.constants

enum class EducationStatus(val status: String) {
    HIGH_SCHOOL_STUDENT("고등학생"),
    UNDERGRADUATE("대학생"),
    GRADUATED("대학교 졸업"),
    OFFICE_WORKER("직장인");

    companion object {
        fun getEducationStatus(idx: Int): EducationStatus {
            return values()[idx]
        }
    }
}