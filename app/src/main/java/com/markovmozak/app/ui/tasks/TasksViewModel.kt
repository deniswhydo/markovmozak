package com.markovmozak.app.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markovmozak.app.data.Category
import com.markovmozak.app.data.Task
import com.markovmozak.app.notifications.MarkoMessages
import com.markovmozak.app.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory: StateFlow<Category?> = _selectedCategory.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val tasks: StateFlow<List<Task>> = _selectedCategory
        .flatMapLatest { category ->
            if (category != null) {
                taskRepository.getTasksByCategory(category)
            } else {
                taskRepository.getAllTasks()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    val activeTaskCount: StateFlow<Int> = taskRepository
        .getActiveTaskCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun selectCategory(category: Category?) {
        _selectedCategory.value = category
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
