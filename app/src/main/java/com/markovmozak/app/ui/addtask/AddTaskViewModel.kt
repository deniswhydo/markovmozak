package com.markovmozak.app.ui.addtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markovmozak.app.data.Category
import com.markovmozak.app.data.Priority
import com.markovmozak.app.data.Task
import com.markovmozak.app.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddTaskUiState(
    val title: String = "",
    val description: String = "",
    val category: Category = Category.OSTALO,
    val priority: Priority = Priority.SREDNJE,
    val dueDate: String? = null,
    val dueTime: String? = null,
    val reminderEnabled: Boolean = false,
    val isRecurring: Boolean = false,
    val recurringDays: String = "",
    val isEditing: Boolean = false,
    val isSaved: Boolean = false
)

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddTaskUiState())
    val uiState: StateFlow<AddTaskUiState> = _uiState.asStateFlow()

    private var editingTaskId: Long? = null

    fun loadTask(taskId: Long) {
        viewModelScope.launch {
            val task = taskRepository.getTaskById(taskId)
            if (task != null) {
                editingTaskId = task.id
                _uiState.value = AddTaskUiState(
                    title = task.title,
                    description = task.description ?: "",
                    category = task.category,
                    priority = task.priority,
                    dueDate = task.dueDate,
                    dueTime = task.dueTime,
                    reminderEnabled = task.reminderEnabled,
                    isRecurring = task.isRecurring,
                    recurringDays = task.recurringDays?.toString() ?: "",
                    isEditing = true
                )
            }
        }
    }

    fun updateTitle(title: String) {
        _uiState.value = _uiState.value.copy(title = title)
    }

    fun updateDescription(description: String) {
        _uiState.value = _uiState.value.copy(description = description)
    }

    fun updateCategory(category: Category) {
        _uiState.value = _uiState.value.copy(category = category)
    }

    fun updatePriority(priority: Priority) {
        _uiState.value = _uiState.value.copy(priority = priority)
    }

    fun updateDueDate(date: String?) {
        _uiState.value = _uiState.value.copy(dueDate = date)
    }

    fun updateDueTime(time: String?) {
        _uiState.value = _uiState.value.copy(dueTime = time)
    }

    fun updateReminderEnabled(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(reminderEnabled = enabled)
    }

    fun updateIsRecurring(recurring: Boolean) {
        _uiState.value = _uiState.value.copy(isRecurring = recurring)
    }

    fun updateRecurringDays(days: String) {
        _uiState.value = _uiState.value.copy(recurringDays = days)
    }

    fun saveTask() {
        val state = _uiState.value
        if (state.title.isBlank()) return

        viewModelScope.launch {
            val task = Task(
                id = editingTaskId ?: 0,
                title = state.title.trim(),
                description = state.description.takeIf { it.isNotBlank() },
                category = state.category,
                priority = state.priority,
                dueDate = state.dueDate,
                dueTime = state.dueTime,
                reminderEnabled = state.reminderEnabled,
                isRecurring = state.isRecurring,
                recurringDays = state.recurringDays.toIntOrNull()
            )

            if (editingTaskId != null) {
                taskRepository.updateTask(task)
            } else {
                taskRepository.insertTask(task)
            }

            _uiState.value = _uiState.value.copy(isSaved = true)
        }
    }
}
