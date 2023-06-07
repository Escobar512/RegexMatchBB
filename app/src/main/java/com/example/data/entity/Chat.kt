package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats")
data class Chat(
    @PrimaryKey val id: String,
    val lookerId: String,
    val offererId: String,
    val lastUpdate: Long
)