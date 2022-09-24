package com.yt8492.commitorban.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import com.yt8492.commitorban.infra.Twitter

@Composable
fun twitterLogin(): suspend () -> Unit {
    val uriHandler = LocalUriHandler.current
    return {
        uriHandler.openUri(Twitter.getLoginUrl())
    }
}
