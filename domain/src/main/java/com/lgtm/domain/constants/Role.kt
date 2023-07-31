package com.lgtm.domain.constants

enum class Role(role: String) {
    REVIEWEE("JUNIOR"),
    REVIEWER("SENIOR");

    companion object {
        fun isProperRole(role: String): Boolean {
            return role == REVIEWEE.name || role == REVIEWER.name
        }
    }
}