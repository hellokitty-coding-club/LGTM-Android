package com.lgtm.android.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultPreference

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class EncryptedPreference

@Module
@InstallIn(SingletonComponent::class)
object LgtmPreferenceModule {

    @Provides
    @Singleton
    @DefaultPreference
    fun provideLgtmPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("lgtm_shared_preference", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    @EncryptedPreference
    fun provideLgtmEncryptedPreference(@ApplicationContext context: Context): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            "lgtm_encrypted_shared_prefs",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}
