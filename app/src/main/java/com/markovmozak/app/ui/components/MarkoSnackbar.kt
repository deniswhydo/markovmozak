package com.markovmozak.app.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.markovmozak.app.ui.theme.MarkoOrange

@Composable
fun MarkoSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier
) {
    Snackbar(
        modifier = modifier.padding(16.dp),
        containerColor = MarkoOrange,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        action = {
            snackbarData.visuals.actionLabel?.let { label ->
                TextButton(onClick = { snackbarData.performAction() }) {
                    Text(
                        text = label,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    ) {
        Text(text = snackbarData.visuals.message)
    }
}
