package com.yt8492.commitorban.infra.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("select * from tasks where done = 0")
    fun getAll(): Flow<List<TaskEntity>>

    @Query("select * from tasks where id = :id")
    fun get(id: String): Flow<TaskEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)
}