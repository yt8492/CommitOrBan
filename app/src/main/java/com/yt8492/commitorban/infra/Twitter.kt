package com.yt8492.commitorban.infra

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import twitter4j.StatusUpdate
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken
import java.io.InputStream

object Twitter {
    private val instance = TwitterFactory().instance

    suspend fun getLoginUrl(): String {
        return withContext(Dispatchers.IO) {
            instance.oAuthRequestToken.authorizationURL
        }
    }

    suspend fun handleLogin(oauthVerifier: String): AccessToken {
        return withContext(Dispatchers.IO) {
            instance.getOAuthAccessToken(oauthVerifier)
        }
    }

    suspend fun tweet(text: String, media: InputStream?) {
        withContext(Dispatchers.IO) {
            val status = StatusUpdate(text).apply {
                if (media != null) {
                    media("file", media)
                }
            }
            instance.updateStatus(status)
        }
    }
}