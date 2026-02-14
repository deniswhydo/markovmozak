package com.markovmozak.app.repository

import com.markovmozak.app.data.ShoppingDao
import com.markovmozak.app.data.ShoppingItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao
) {
    fun getAllItems(): Flow<List<ShoppingItem>> = shoppingDao.getAllItems()

    fun getUncheckedCount(): Flow<Int> = shoppingDao.getUncheckedCount()

    suspend fun insertItem(item: ShoppingItem): Long = shoppingDao.insertItem(item)

    suspend fun updateItem(item: ShoppingItem) = shoppingDao.updateItem(item)

    suspend fun deleteItem(item: ShoppingItem) = shoppingDao.deleteItem(item)

    suspend fun setChecked(id: Long, checked: Boolean) = shoppingDao.setChecked(id, checked)

    suspend fun deleteCheckedItems() = shoppingDao.deleteCheckedItems()
}
