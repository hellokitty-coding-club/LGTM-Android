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
        isEncrypted: Boolean,
    ): T {
        val preference = getPreference(isEncrypted)
        val key = preferenceKey.name
        return when (defaultValue) {
            is String -> preference.getString(key, defaultValue) as T
            is Boolean -> preference.getBoolean(key, defaultValue) as T
            is Int -> preference.getInt(key, defaultValue) as T
            else -> throw IllegalArgumentException("Add data type on ${this.javaClass.simpleName}")
        }
    }

    fun <T> setValue(
        preferenceKey: PreferenceKey,
        value: T,
        isEncrypted: Boolean,
        byAsync: Boolean
    ) {
        val preference = getPreference(isEncrypted)
        val key = preferenceKey.name
        when (value) {
            is String -> preference.edit(byAsync) { putString(key, value) }
            is Boolean -> preference.edit(byAsync) { putBoolean(key, value) }
            is Int -> preference.edit(byAsync) { putInt(key, value) }
            else -> throw IllegalArgumentException("Add data type on ${this.javaClass.simpleName}")
        }
    }

    fun clearValue(
        isEncrypted: Boolean,
    ) {
        val preference = getPreference(isEncrypted)
        preference.edit().clear().apply()
    }

    private fun getPreference(isEncrypted: Boolean): SharedPreferences {
        return if (isEncrypted) lgtmEncryptedPreferences else lgtmPreference
    }

    companion object {
        enum class PreferenceKey {
            MEMBER_TYPE,
            MEMBER_ID,
            UNKNOWN_MEMBER_TYPE,
            ACCESS_TOKEN,
            REFRESH_TOKEN;
        }
    }

    private inline fun SharedPreferences.edit(
        byAsync: Boolean, operation: SharedPreferences.Editor.() -> Unit
    ) {
        val editor = edit()
        operation(editor)
        if (byAsync)
            editor.apply()
        else
            editor.commit()
    }
}
