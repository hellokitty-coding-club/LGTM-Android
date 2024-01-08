package com.lgtm.domain.server_drive_ui

import java.lang.reflect.Type

enum class SduiViewType(
    val viewType: String, private val viewTypeClassType: Type
) {
    TITLE("sectionTitle", SectionTitleVO::class.java),
    EMPTY("empty", SectionEmptyVO::class.java),
    CLOSER("sectionCloser", SectionCloserVO::class.java),
    ITEM("sectionItem", SectionItemVO::class.java),
    SUBITEM("sectionSubItem", SectionSubItemVO::class.java),
    UNKNOWN("sectionUnknown", SectionUnknownVO::class.java);

    companion object {
        fun getViewTypeByOrdinal(ordinalNum: Int): SduiViewType {
            return values()[ordinalNum]
        }

        fun SduiViewType.getViewTypeClassType(): Type {
            return this.viewTypeClassType
        }

        fun findClassByItsName(viewTypeString: String?): SduiViewType {
            values().find { it.viewType == viewTypeString }?.let {
                return it
            } ?: return UNKNOWN
        }
    }
}