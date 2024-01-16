package com.lgtm.domain.mission_suggestion

enum class SuggestionViewType {
    HEADER,
    CONTENT;

    companion object {
        fun getViewTypeByOrdinal(ordinal: Int): SuggestionViewType {
            return values()[ordinal]
        }
    }
}