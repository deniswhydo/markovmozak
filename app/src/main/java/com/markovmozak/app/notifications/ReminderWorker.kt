package com.markovmozak.app.notifications

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.markovmozak.app.repository.TaskRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

@HiltWorker
class ReminderWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val taskRepository: TaskRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val tasks = taskRepository.getTasksWithReminders()

        for (task in tasks) {
            // Escalate if needed
            val newLevel = (task.escalationLevel + 1).coerceAtMost(3)
            taskRepository.updateEscalationLevel(task.id, newLevel)

            NotificationHelper.showTaskReminder(
                context = applicationContext,
                taskId = task.id,
                taskTitle = task.title,
                escalationLevel = task.escalationLevel
            )
        }

        return Result.success()
    }

    companion object {
        private const val WORK_NAME = "marko_reminder_check"

        fun schedule(context: Context) {
            val request = PeriodicWorkRequestBuilder<ReminderWorker>(
                4, TimeUnit.HOURS
            ).build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
        }
    }
}
