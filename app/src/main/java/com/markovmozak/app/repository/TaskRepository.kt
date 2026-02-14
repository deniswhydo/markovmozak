package com.markovmozak.app.repository

import com.markovmozak.app.data.Category
import com.markovmozak.app.data.Task
import com.markovmozak.app.data.TaskDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    fun getActiveTasks(): Flow<List<Task>> = taskDao.getActiveTasks()

    fun getTasksForDate(date: String): Flow<List<Task>> = taskDao.getTasksForDate(date)

    fun getTasksByCategory(category: Category): Flow<List<Task>> = taskDao.getTasksByCategory(category)

    fun getActiveTaskCount(): Flow<Int> = taskDao.getActiveTaskCount()

    suspend fun getTaskById(id: Long): Task? = taskDao.getTaskById(id)

    suspend fun getTasksWithReminders(): List<Task> = taskDao.getTasksWithReminders()

    suspend fun insertTask(task: Task): Long = taskDao.insertTask(task)

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)

    suspend fun completeTask(id: Long) = taskDao.completeTask(id)

    suspend fun uncompleteTask(id: Long) = taskDao.uncompleteTask(id)

    suspend fun updateEscalationLevel(id: Long, level: Int) = taskDao.updateEscalationLevel(id, level)
}
