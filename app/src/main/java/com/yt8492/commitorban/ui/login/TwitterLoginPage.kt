package com.yt8492.commitorban.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.yt8492.commitorban.infra.LocalAccessTokenPreferences
import com.yt8492.commitorban.infra.Twitter
import kotlinx.coroutines.launch

@Composable
fun TwitterLoginPage(
    navController: NavController,
) {
    val coroutineScope = rememberCoroutineScope()
    val login = twitterLogin()
    val token = LocalAccessTokenPreferences.current.watch()
    LaunchedEffect(token) {
        if (token != null) {
            Twitter.storeToken(token)
            navController.navigate("taskList")
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ログイン")
                },
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        login()
                    }
                }
            ) {
                Text(text = "Twitterでログイン")
            }
        }
    }
}
