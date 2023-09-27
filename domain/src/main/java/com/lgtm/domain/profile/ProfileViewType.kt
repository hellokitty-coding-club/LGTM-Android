package com.lgtm.domain.profile

enum class ProfileViewType {
    PROFILE_IMAGE,
    PROFILE_GLANCE,
    THICK_DIVIDER,
    PROFILE_TITLE_TEXT,
    TECH_TAG_LIST,
    THIN_DIVIDER,
    PROFILE_DETAIL_TEXT,
    SECTION_ITEM_VO;

    companion object {
        fun getViewTypeByOrdinal(ordinalNum: Int): ProfileViewType {
            return ProfileViewType.values()[ordinalNum]
        }
    }
}