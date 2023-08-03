package com.lgtm.android.data.datasource

import android.content.SharedPreferences
import javax.inject.Inject

class LgtmPreferenceDataSource @Inject constructor(
    private val lgtmPreference: SharedPreferences,
    private val lgtmEncryptedPreferences: SharedPreferences
) {

    fun <T> getValue(
        preferenceKey: PreferenceKey,
        defaultValue: T,
        isEncrypted: Boolean = false
    ): T {
        val preference = if (isEncrypted) lgtmEncryptedPreferences else lgtmPreference
        val key = preferenceKey.name
        return when (defaultValue) {
            is String -> preference.getString(key, defaultValue) as T
            is Boolean -> preference.getBoolean(key, defaultValue) as T
            else -> throw IllegalArgumentException("Add data type on ${this.javaClass.simpleName}}")
        }
    }

    fun <T> setValue(preferenceKey: PreferenceKey, value: T, isEncrypted: Boolean = false) {
        val preference = if (isEncrypted) lgtmEncryptedPreferences else lgtmPreference
        val key = preferenceKey.name
        when (value) {
            is String -> preference.edit { putString(key, value) }
            is Boolean -> preference.edit { putBoolean(key, value) }
            else -> throw IllegalArgumentException("Add data type on ${this.javaClass.simpleName}}")
        }
    }

    companion object {
        enum class PreferenceKey {
            MEMBER_TYPE,
            UNKNOWN_MEMBER_TYPE,
            IS_AUTO_LOGIN,
            ACCESS_TOKEN,
            REFRESH_TOKEN;
        }
    }

    private inline fun SharedPreferences.edit(operation: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }
}
