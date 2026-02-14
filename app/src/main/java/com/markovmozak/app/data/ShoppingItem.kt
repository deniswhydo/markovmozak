package com.markovmozak.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val isChecked: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
