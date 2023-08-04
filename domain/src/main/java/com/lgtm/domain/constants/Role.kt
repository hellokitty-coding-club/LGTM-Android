package com.lgtm.domain.constants

enum class Role(val role: String) {
    REVIEWEE("JUNIOR"), REVIEWER("SENIOR");

    companion object {
        fun isProperRole(role: String): Boolean {
            return role == REVIEWEE.role || role == REVIEWER.role
        }
    }
}