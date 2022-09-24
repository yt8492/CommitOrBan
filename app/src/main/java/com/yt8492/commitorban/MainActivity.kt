package com.yt8492.commitorban

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.yt8492.commitorban.ui.App
import com.yt8492.commitorban.ui.theme.CommitOrBanTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CommitOrBanTheme {
                App()
            }
        }
    }
}
