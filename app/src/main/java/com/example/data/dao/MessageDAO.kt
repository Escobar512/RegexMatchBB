package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.Message

@Dao
interface MessageDAO {
    @Query("SELECT * FROM messages WHERE chatId = :chatId")
    suspend fun getMessagesByChatId(chatId: String): List<Message>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMessages(messages: List<Message>)
}