package com.yt8492.commitorban.infra.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val content: String,
    val due: Instant,
    @ColumnInfo(name = "punishment_uri")
    val punishmentUri: String,
    val done: Boolean,
)
