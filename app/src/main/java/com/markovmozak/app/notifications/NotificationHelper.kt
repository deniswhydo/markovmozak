package com.markovmozak.app.notifications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.markovmozak.app.MainActivity
import com.markovmozak.app.MarkovMozakApp

object NotificationHelper {

    fun showTaskReminder(
        context: Context,
        taskId: Long,
        taskTitle: String,
        escalationLevel: Int
    ) {
        val message = MarkoMessages.getEscalatingReminder(taskTitle, escalationLevel)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, taskId.toInt(), intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val priority = when (escalationLevel) {
            0 -> NotificationCompat.PRIORITY_DEFAULT
            1 -> NotificationCompat.PRIORITY_HIGH
            else -> NotificationCompat.PRIORITY_MAX
        }

        val notification = NotificationCompat.Builder(context, MarkovMozakApp.CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("🧠 MarkovMozak")
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(priority)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        try {
            NotificationManagerCompat.from(context).notify(taskId.toInt(), notification)
        } catch (_: SecurityException) {
            // Permission not granted
        }
    }
}
