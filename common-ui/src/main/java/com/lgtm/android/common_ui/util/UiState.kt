package com.lgtm.android.common_ui.util

sealed interface UiState<out T> {
    object Init: UiState<Nothing>

    data class Success<T>(
        val data: T
    ): UiState<T>

    data class Failure(
        val msg: String?
    ): UiState<Nothing>
}