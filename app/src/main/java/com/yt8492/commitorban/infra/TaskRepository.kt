package com.yt8492.commitorban.infra

import android.net.Uri
import androidx.compose.runtime.compositionLocalOf
import com.yt8492.commitorban.domain.model.Task
import com.yt8492.commitorban.infra.room.TaskDao
import com.yt8492.commitorban.infra.room.TaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepository(private val taskDao: TaskDao) {

    fun getAll(): Flow<List<Task>> {
        return taskDao.getAll()
            .map {
                it.map {
                    convertToDomainModel(it)
                }
            }
    }

    fun get(id: String): Flow<Task?> {
        return taskDao.get(id)
            .map {
                it?.let {
                    convertToDomainModel(it)
                }
            }
    }

    suspend fun save(task: Task) {
        taskDao.save(convertToEntity(task))
    }

    private fun convertToEntity(task: Task): TaskEntity {
        val punishment = task.punishment as PunishmentImage
        return TaskEntity(
            id = task.id,
            title = task.title,
            content = task.content,
            due = task.due,
            punishmentUri = punishment.uri.toString(),
            done = task.done,
        )
    }

    private fun convertToDomainModel(taskEntity: TaskEntity): Task {
        return Task(
            id = taskEntity.id,
            title = taskEntity.title,
            content = taskEntity.content,
            due = taskEntity.due,
            punishment = PunishmentImage(Uri.parse(taskEntity.punishmentUri)),
            done = taskEntity.done,
        )
    }
}

val LocalTaskRepository = compositionLocalOf<TaskRepository> {
    error("must be provided")
}
