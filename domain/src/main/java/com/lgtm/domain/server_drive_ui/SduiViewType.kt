package com.lgtm.domain.server_drive_ui

import java.lang.reflect.Type

enum class SduiViewType(
    val viewType: String, private val viewTypeClassType: Type
) {
    TITLE("sectionTitle", SectionTitleVO::class.java),
    EMPTY("sectionEmpty", SectionEmptyVO::class.java),
    CLOSER("sectionCloser", SectionCloserVO::class.java),
    ITEM("sectionItem", SectionItemVO::class.java),
    UNKNOWN("sectionUnknown", SectionUnknownVO::class.java);

    companion object {
        fun getViewTypeByOrdinal(ordinalNum: Int): SduiViewType {
            return values()[ordinalNum]
        }

        fun findClassTypeByViewType(viewType: String): Type {
            values().find { it.viewType == viewType }?.let {
                return it.viewTypeClassType
            } ?: return UNKNOWN.viewTypeClassType
        }

    }
}