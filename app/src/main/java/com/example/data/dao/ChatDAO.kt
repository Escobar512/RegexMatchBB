package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.Chat

@Dao
interface ChatDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(chat: Chat)

    @Query("SELECT * FROM chats WHERE id = :chatId")
    suspend fun getChatById(chatId: String): Chat?

    @Query("SELECT * FROM chats WHERE lookerId = :lookerId AND offererId = :offererId")
    suspend fun getChatByUsers(lookerId: String, offererId: String): Chat?
}