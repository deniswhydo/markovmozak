package com.markovmozak.app.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.room.Room
import com.markovmozak.app.MainActivity
import com.markovmozak.app.data.AppDatabase
import com.markovmozak.app.notifications.MarkoMessages
import com.markovmozak.app.util.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarkoWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val db = Room.databaseBuilder(
            context, AppDatabase::class.java, "markovmozak_db"
        ).build()

        val todayTasks = withContext(Dispatchers.IO) {
            db.taskDao().getTasksForDateOnce(DateUtils.today())
        }
        val activeCount = withContext(Dispatchers.IO) {
            db.taskDao().getActiveTaskCountOnce()
        }

        provideContent {
            GlanceTheme {
                WidgetContent(
                    todayTaskTitles = todayTasks.take(5).map { "${it.category.emoji} ${it.title}" },
                    activeCount = activeCount
                )
            }
        }
    }
}

@Composable
private fun WidgetContent(
    todayTaskTitles: List<String>,
    activeCount: Int
) {
    val orange = ColorProvider(Color(0xFFFF6B35))
    val cream = ColorProvider(Color(0xFFFFF8F0))
    val darkText = ColorProvider(Color(0xFF424242))

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(cream)
            .padding(12.dp)
            .clickable(actionStartActivity<MainActivity>()),
        verticalAlignment = Alignment.Top
    ) {
        // Header
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "🧠 MarkovMozak",
                style = TextStyle(
                    color = orange,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = GlanceModifier.defaultWeight())
            Text(
                text = "$activeCount zadataka",
                style = TextStyle(
                    color = darkText,
                    fontSize = 12.sp
                )
            )
        }

        Spacer(modifier = GlanceModifier.height(6.dp))

        // Today's tasks
        if (todayTaskTitles.isEmpty()) {
            Text(
                text = "Nema zadataka za danas! 🎉",
                style = TextStyle(color = darkText, fontSize = 13.sp)
            )
        } else {
            todayTaskTitles.forEach { title ->
                Text(
                    text = "• $title",
                    style = TextStyle(color = darkText, fontSize = 13.sp),
                    maxLines = 1
                )
            }
            if (activeCount > todayTaskTitles.size) {
                Text(
                    text = "  + još ${activeCount - todayTaskTitles.size}...",
                    style = TextStyle(color = orange, fontSize = 12.sp)
                )
            }
        }
    }
}

class MarkoWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MarkoWidget()
}
