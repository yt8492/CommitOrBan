package com.yt8492.commitorban.ui.login

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.yt8492.commitorban.infra.LocalAccessTokenPreferences
import com.yt8492.commitorban.infra.Twitter

@Composable
fun HandleTwitterLogin(
    oauthVerifier: String,
    navController: NavController,
) {
    val pref = LocalAccessTokenPreferences.current
    LaunchedEffect(oauthVerifier) {
        try {
            val accessToken = Twitter.handleLogin(oauthVerifier)
            pref.store(accessToken)
            navController.navigate("taskList")
        } catch(e: Exception) {
            Log.e("hogehoge", "error: ${e.message}", e)
        }
    }
}
