package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey val messageId: String,
    val sender: String,
    val receiver: String,
    val message: String,
    val chatId: String
)