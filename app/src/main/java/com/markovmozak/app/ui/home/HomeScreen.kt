package com.markovmozak.app.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.markovmozak.app.ui.components.FunnyEmptyState
import com.markovmozak.app.ui.components.MarkoSnackbar
import com.markovmozak.app.ui.components.TaskCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToAllTasks: () -> Unit,
    onNavigateToAddTask: () -> Unit,
    onNavigateToShopping: () -> Unit,
    onNavigateToEditTask: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val todayTasks by viewModel.todayTasks.collectAsStateWithLifecycle()
    val activeTaskCount by viewModel.activeTaskCount.collectAsStateWithLifecycle()
    val snackbarMessage by viewModel.snackbarMessage.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    var greeting by remember { mutableStateOf(viewModel.getGreeting()) }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearSnackbar()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "🧠 MarkovMozak",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = onNavigateToShopping) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Kupovina")
                    }
                    IconButton(onClick = onNavigateToAllTasks) {
                        Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Svi zadaci")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNavigateToAddTask,
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text("Novi zadatak") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                MarkoSnackbar(snackbarData = data)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Greeting
            item {
                Column {
                    Text(
                        text = greeting,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    val taskMessage = viewModel.getTaskCountMessage(activeTaskCount)
                    if (taskMessage != null) {
                        Text(
                            text = taskMessage,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Quick action buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        QuickActionChip(
                            emoji = "📋",
                            label = "Svi zadaci ($activeTaskCount)",
                            onClick = onNavigateToAllTasks,
                            modifier = Modifier.weight(1f)
                        )
                        QuickActionChip(
                            emoji = "🛒",
                            label = "Kupovina",
                            onClick = onNavigateToShopping,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Today header
            item {
                Text(
                    text = "📅 Danas",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Today's tasks or empty state
            if (todayTasks.isEmpty()) {
                item {
                    FunnyEmptyState(
                        emoji = "🎉",
                        message = "Nema zadataka za danas! Ili si sve riješio, ili nisi ništa upisao..."
                    )
                }
            } else {
                items(todayTasks, key = { it.id }) { task ->
                    TaskCard(
                        task = task,
                        onToggleComplete = { viewModel.toggleTaskComplete(it) },
                        onEdit = { onNavigateToEditTask(it.id) },
                        onDelete = { viewModel.deleteTask(it) }
                    )
                }
            }
        }
    }
}

@Composable
private fun QuickActionChip(
    emoji: String,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.OutlinedButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text("$emoji $label")
    }
}
