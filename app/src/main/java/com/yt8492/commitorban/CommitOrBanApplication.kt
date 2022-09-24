package com.yt8492.commitorban

import android.app.Application
import androidx.room.Room
import com.yt8492.commitorban.infra.room.AppDatabase

class CommitOrBanApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.INSTANCE = Room
            .databaseBuilder(this, AppDatabase::class.java, "commit_or_ban")
            .build()
    }
}
