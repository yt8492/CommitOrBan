package com.yt8492.commitorban.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun TwitterLoginPage() {
    val coroutineScope = rememberCoroutineScope()
    val login = twitterLogin()
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
