package com.yt8492.commitorban.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun TimeTickSecEventReceiver(
    onTimeTickEverySecEvent: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(context) {
        while (true) {
            delay(1.seconds)
            onTimeTickEverySecEvent()
        }
    }
}
