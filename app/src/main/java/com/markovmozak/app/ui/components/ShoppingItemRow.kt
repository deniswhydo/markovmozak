package com.markovmozak.app.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.markovmozak.app.data.ShoppingItem
import com.markovmozak.app.ui.theme.MarkoGreen
import com.markovmozak.app.ui.theme.MarkoOrange
import com.markovmozak.app.ui.theme.MarkoRed

@Composable
fun ShoppingItemRow(
    item: ShoppingItem,
    onToggleChecked: (ShoppingItem) -> Unit,
    onDelete: (ShoppingItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.isChecked,
            onCheckedChange = { onToggleChecked(item) },
            colors = CheckboxDefaults.colors(
                checkedColor = MarkoGreen,
                uncheckedColor = MarkoOrange
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = item.name,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,
            textDecoration = if (item.isChecked) TextDecoration.LineThrough else null,
            color = if (item.isChecked) {
                MaterialTheme.colorScheme.onSurfaceVariant
            } else {
                MaterialTheme.colorScheme.onSurface
            }
        )

        IconButton(
            onClick = { onDelete(item) },
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                Icons.Default.Close,
                contentDescription = "Obriši",
                tint = MarkoRed
            )
        }
    }
}
