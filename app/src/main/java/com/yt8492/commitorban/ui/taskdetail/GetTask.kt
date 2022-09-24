package com.yt8492.commitorban.ui.taskdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.yt8492.commitorban.domain.model.Task
import com.yt8492.commitorban.infra.LocalTaskRepository

@Composable
fun getTask(id: String): Task? {
    val repository = LocalTaskRepository.current
    val task by repository.get(id)
        .collectAsState(initial = null)
    return task
}
