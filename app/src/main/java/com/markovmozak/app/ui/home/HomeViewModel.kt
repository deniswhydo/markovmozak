package com.markovmozak.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markovmozak.app.data.Task
import com.markovmozak.app.notifications.MarkoMessages
import com.markovmozak.app.repository.TaskRepository
import com.markovmozak.app.util.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val todayTasks: StateFlow<List<Task>> = taskRepository
        .getTasksForDate(DateUtils.today())
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeTaskCount: StateFlow<Int> = taskRepository
        .getActiveTaskCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    fun getGreeting(): String = MarkoMessages.getGreeting()

    fun getTaskCountMessage(count: Int): String? {
        return when {
            count == 0 -> MarkoMessages.getEmptyTaskMessage()
            count > 5 -> MarkoMessages.getManyTasksMessage(count)
            else -> null
        }
    }

    fun toggleTaskComplete(task: Task) {
        viewModelScope.launch {
            if (task.isCompleted) {
                taskRepository.uncompleteTask(task.id)
            } else {
                taskRepository.completeTask(task.id)
                val remaining = activeTaskCount.value - 1
                _snackbarMessage.value = MarkoMessages.getCompletionMessage(remaining)
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
            _snackbarMessage.value = MarkoMessages.getDeleteMessage()
        }
    }

    fun clearSnackbar() {
        _snackbarMessage.value = null
    }
}
