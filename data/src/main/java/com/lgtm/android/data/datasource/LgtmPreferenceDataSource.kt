package com.lgtm.android.data.datasource

import android.content.SharedPreferences
import javax.inject.Inject

class LgtmPreferenceDataSource @Inject constructor(
    private val lgtmPreference: SharedPreferences,
    private val lgtmEncryptedPreferences: SharedPreferences
) {

    fun getMemberType(): String {
        return lgtmPreference.getString(MEMBER_TYPE, UNKNOWN_MEMBER_TYPE) ?: UNKNOWN_MEMBER_TYPE
    }

    fun setMemberType(value: String) {
        lgtmPreference.edit { putString(MEMBER_TYPE, value) }
    }

    fun isAutoLogin(): Boolean {
        return lgtmPreference.getBoolean(IS_AUTO_LOGIN, false)
    }

    fun setAutoLogin(value: Boolean) {
        lgtmPreference.edit { putBoolean(IS_AUTO_LOGIN, value) }
    }

    fun getAccessToken(): String {
        val token = lgtmEncryptedPreferences.getString(ACCESS_TOKEN, "") ?: ""
        return if (token.isEmpty()) "" else "Bearer $token"
    }

    fun setAccessToken(value: String) {
        lgtmEncryptedPreferences.edit { putString(ACCESS_TOKEN, value) }
    }

    fun getRefreshToken(): String {
        return lgtmEncryptedPreferences.getString(REFRESH_TOKEN, "") ?: ""
    }

    fun setRefreshToken(value: String) {
        lgtmEncryptedPreferences.edit { putString(REFRESH_TOKEN, value) }
    }

    companion object {
        private const val MEMBER_TYPE = "member_type"
        private const val UNKNOWN_MEMBER_TYPE = "unknown_member_type"
        private const val IS_AUTO_LOGIN = "isAutoLogin"
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
    }

    private inline fun SharedPreferences.edit(operation: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }
}
