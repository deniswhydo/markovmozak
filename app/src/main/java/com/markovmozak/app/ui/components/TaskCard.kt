package com.markovmozak.app.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.markovmozak.app.data.Priority
import com.markovmozak.app.data.Task
import com.markovmozak.app.ui.theme.MarkoGreen
import com.markovmozak.app.ui.theme.MarkoGrayLight
import com.markovmozak.app.ui.theme.MarkoOrange
import com.markovmozak.app.ui.theme.MarkoRed
import com.markovmozak.app.util.DateUtils

@Composable
fun TaskCard(
    task: Task,
    onToggleComplete: (Task) -> Unit,
    onEdit: (Task) -> Unit,
    onDelete: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    val isOverdue = !task.isCompleted && DateUtils.isOverdue(task.dueDate)

    val containerColor by animateColorAsState(
        targetValue = when {
            task.isCompleted -> MarkoGrayLight
            isOverdue -> MarkoRed.copy(alpha = 0.1f)
            task.priority == Priority.HITNO -> MarkoOrange.copy(alpha = 0.1f)
            else -> MaterialTheme.colorScheme.surface
        },
        label = "cardColor"
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { onToggleComplete(task) },
                colors = CheckboxDefaults.colors(
                    checkedColor = MarkoGreen,
                    uncheckedColor = MarkoOrange
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "${task.category.emoji} ${task.title}",
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = task.priority.label(),
                        style = MaterialTheme.typography.bodySmall
                    )

                    if (task.dueDate != null) {
                        Text(
                            text = DateUtils.formatDateDisplay(task.dueDate),
                            style = MaterialTheme.typography.bodySmall,
                            color = if (isOverdue) MarkoRed else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    if (task.dueTime != null) {
                        Text(
                            text = task.dueTime,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            IconButton(
                onClick = { onEdit(task) },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Uredi",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            IconButton(
                onClick = { onDelete(task) },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Obriši",
                    tint = MarkoRed
                )
            }
        }
    }
}
