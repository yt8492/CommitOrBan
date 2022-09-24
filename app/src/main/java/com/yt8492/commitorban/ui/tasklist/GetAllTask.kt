package com.yt8492.commitorban.ui.tasklist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.yt8492.commitorban.domain.model.Task
import com.yt8492.commitorban.infra.LocalTaskRepository

@Composable
fun getAllTask(): List<Task> {
    val repository = LocalTaskRepository.current
    val tasks by repository.getAll()
        .collectAsState(initial = listOf())
    return tasks
}
