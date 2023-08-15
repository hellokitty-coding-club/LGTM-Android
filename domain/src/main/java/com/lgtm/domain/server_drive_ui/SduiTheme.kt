package com.lgtm.domain.server_drive_ui

enum class SduiTheme {
    WHITE, GRAY;

    companion object {
        fun findClassByItsName(themeString: String?): SduiTheme {
            return when (themeString) {
                "WHITE" -> WHITE
                "GRAY" -> GRAY
                else -> WHITE
            }
        }
    }
}
