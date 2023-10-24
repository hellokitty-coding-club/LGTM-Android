package com.lgtm.domain.profile

enum class ProfileViewType {
    BIG_PROFILE_IMAGE,
    PROFILE_GLANCE,
    THICK_DIVIDER,
    TITLE_TEXT,
    TECH_TAG_LIST,
    THIN_DIVIDER,
    DETAIL_TEXT,
    SECTION_ITEM_VO,
    MISSION_EMPTY;

    companion object {
        fun getViewTypeByOrdinal(ordinalNum: Int): ProfileViewType {
            return ProfileViewType.values()[ordinalNum]
        }
    }
}