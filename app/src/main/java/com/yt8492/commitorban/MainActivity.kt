package com.yt8492.commitorban

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import com.yt8492.commitorban.infra.LocalTaskRepository
import com.yt8492.commitorban.infra.TaskRepository
import com.yt8492.commitorban.infra.room.AppDatabase
import com.yt8492.commitorban.ui.App
import com.yt8492.commitorban.ui.theme.CommitOrBanTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CommitOrBanTheme {
                CompositionLocalProvider(
                    LocalTaskRepository provides TaskRepository(AppDatabase.INSTANCE.taskDao())
                ) {
                    App()
                }
            }
        }
    }
}
