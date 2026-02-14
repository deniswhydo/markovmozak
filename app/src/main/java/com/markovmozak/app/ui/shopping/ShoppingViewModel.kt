package com.markovmozak.app.ui.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markovmozak.app.data.ShoppingItem
import com.markovmozak.app.notifications.MarkoMessages
import com.markovmozak.app.repository.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : ViewModel() {

    val items: StateFlow<List<ShoppingItem>> = shoppingRepository
        .getAllItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val uncheckedCount: StateFlow<Int> = shoppingRepository
        .getUncheckedCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    private val _newItemText = MutableStateFlow("")
    val newItemText: StateFlow<String> = _newItemText.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    fun updateNewItemText(text: String) {
        _newItemText.value = text
    }

    fun addItem() {
        val text = _newItemText.value.trim()
        if (text.isBlank()) return

        viewModelScope.launch {
            shoppingRepository.insertItem(ShoppingItem(name = text))
            _newItemText.value = ""
        }
    }

    fun toggleChecked(item: ShoppingItem) {
        viewModelScope.launch {
            shoppingRepository.setChecked(item.id, !item.isChecked)

            // Check if all items are now checked
            if (!item.isChecked && uncheckedCount.value <= 1) {
                _snackbarMessage.value = MarkoMessages.getShoppingDoneMessage()
            }
        }
    }

    fun deleteItem(item: ShoppingItem) {
        viewModelScope.launch {
            shoppingRepository.deleteItem(item)
        }
    }

    fun deleteCheckedItems() {
        viewModelScope.launch {
            shoppingRepository.deleteCheckedItems()
        }
    }

    fun clearSnackbar() {
        _snackbarMessage.value = null
    }
}
