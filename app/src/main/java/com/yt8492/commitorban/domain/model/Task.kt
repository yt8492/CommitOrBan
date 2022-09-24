package com.yt8492.commitorban.domain.model

import java.time.Instant
import java.time.temporal.ChronoUnit

data class Task(
    val id: String,
    val title: String,
    val content: String,
    val due: Instant,
    val punishment: Punishment,
)

val dummyTask1 = Task(
    id = "1",
    title = "腹筋百回",
    content = "腹筋百回します",
    due = Instant.now().plus(2, ChronoUnit.MINUTES),
    punishment = object : Punishment {},
)

val dummyTask2 = Task(
    id = "2",
    title = "スクワット百回",
    content = "スクワット百回します",
    due = Instant.now().plus(30, ChronoUnit.MINUTES),
    punishment = object : Punishment {},
)
