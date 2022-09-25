package com.yt8492.commitorban.infra

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.compose.runtime.*
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import twitter4j.auth.AccessToken

class AccessTokenPreferences(context: Context) {
    private val pref = EncryptedSharedPreferences.create(
        "twitter_access_tokens",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )

    fun store(token: AccessToken) {
        pref.edit {
            putString(KEY_TOKEN, token.token)
            putString(KEY_TOKEN_SECRET, token.tokenSecret)
        }
    }

    fun restore(): AccessToken? {
        val token = pref.getString(KEY_TOKEN, null) ?: return null
        val tokenSecret = pref.getString(KEY_TOKEN_SECRET, null) ?: return null
        return AccessToken(token, tokenSecret)
    }

    @Composable
    fun watch(): AccessToken? {
        val (accessToken, setAccessToken) = remember {
            mutableStateOf<AccessToken?>(null)
        }
        DisposableEffect(Unit) {
            setAccessToken(restore())
            val listener = object : OnSharedPreferenceChangeListener {
                override fun onSharedPreferenceChanged(pref: SharedPreferences, s: String?) {
                    val token = pref.getString(KEY_TOKEN, null) ?: return
                    val tokenSecret = pref.getString(KEY_TOKEN_SECRET, null) ?: return
                    setAccessToken(AccessToken(token, tokenSecret))
                }
            }
            pref.registerOnSharedPreferenceChangeListener(listener)
            return@DisposableEffect onDispose {
                pref.unregisterOnSharedPreferenceChangeListener(listener)
            }
        }
        return accessToken
    }

    companion object {
        private const val KEY_TOKEN = "token"
        private const val KEY_TOKEN_SECRET = "token_secret"
    }
}

val LocalAccessTokenPreferences = compositionLocalOf<AccessTokenPreferences> {
    error("must not be null")
}
