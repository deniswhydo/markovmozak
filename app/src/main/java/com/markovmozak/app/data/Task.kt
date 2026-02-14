package com.markovmozak.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val category: Category = Category.OSTALO,
    val priority: Priority = Priority.SREDNJE,
    val dueDate: String? = null,
    val dueTime: String? = null,
    val isCompleted: Boolean = false,
    val isRecurring: Boolean = false,
    val recurringDays: Int? = null,
    val reminderEnabled: Boolean = false,
    val escalationLevel: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null
)
