package com.darkdeveloper.kawaiianimes.data.security

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.darkdeveloper.kawaiianimes.domain.model.User
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SecureTokenStore @Inject constructor(
    private val context: Context
) {

    private val encryptedPrefs by lazy {
        val masterKeyAlias = MasterKeys.getOrCreate(KeyGenParameterSpec.Builder(
            "master_key",
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .build()
        )

        EncryptedSharedPreferences.create(
            "secure_prefs",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private val json = Json { ignoreUnknownKeys = true }

    fun saveToken(token: String) {
        encryptedPrefs.edit().putString("auth_token", token).apply()
    }

    fun getToken(): String? {
        return encryptedPrefs.getString("auth_token", null)
    }

    fun saveUser(user: User) {
        val jsonString = json.encodeToString(user)
        encryptedPrefs.edit().putString("current_user", jsonString).apply()
    }

    fun getUser(): User? {
        val jsonString = encryptedPrefs.getString("current_user", null)
        return jsonString?.let { json.decodeFromString<User>(it) }
    }

    fun clear() {
        encryptedPrefs.edit().clear().apply()
    }
}