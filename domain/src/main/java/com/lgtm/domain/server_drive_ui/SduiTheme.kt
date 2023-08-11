package com.lgtm.domain.server_drive_ui

enum class SduiTheme {
    DARK, LIGHT;

    companion object {
        fun findClassByItsName(themeString: String?): SduiTheme {
            return when (themeString) {
                "DARK" -> DARK
                "LIGHT" -> LIGHT
                else -> LIGHT
            }
        }
    }
}
