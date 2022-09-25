package com.yt8492.commitorban.ui.taskdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.yt8492.commitorban.domain.model.Task
import com.yt8492.commitorban.infra.LocalTaskRepository

@Composable
fun deleteTask(): DeleteTaskResult {
    val repository = LocalTaskRepository.current
    val (finished, setFinished) = remember {
        mutableStateOf(false)
    }
    return DeleteTaskResult(
        done = finished,
        delete = {
            setFinished(true)
            repository.delete(it)
        }
    )
}

data class DeleteTaskResult(
    val done: Boolean,
    val delete: suspend (Task) -> Unit,
)
