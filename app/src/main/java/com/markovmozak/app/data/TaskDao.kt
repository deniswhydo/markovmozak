package com.markovmozak.app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY isCompleted ASC, priority DESC, createdAt DESC")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY priority DESC, createdAt DESC")
    fun getActiveTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isCompleted = 0 AND dueDate = :date ORDER BY priority DESC")
    fun getTasksForDate(date: String): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isCompleted = 0 AND category = :category ORDER BY priority DESC")
    fun getTasksByCategory(category: Category): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: Long): Task?

    @Query("SELECT COUNT(*) FROM tasks WHERE isCompleted = 0")
    fun getActiveTaskCount(): Flow<Int>

    @Query("SELECT * FROM tasks WHERE reminderEnabled = 1 AND isCompleted = 0")
    suspend fun getTasksWithReminders(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("UPDATE tasks SET isCompleted = 1, completedAt = :completedAt WHERE id = :id")
    suspend fun completeTask(id: Long, completedAt: Long = System.currentTimeMillis())

    @Query("UPDATE tasks SET isCompleted = 0, completedAt = NULL WHERE id = :id")
    suspend fun uncompleteTask(id: Long)

    @Query("UPDATE tasks SET escalationLevel = :level WHERE id = :id")
    suspend fun updateEscalationLevel(id: Long, level: Int)
}
