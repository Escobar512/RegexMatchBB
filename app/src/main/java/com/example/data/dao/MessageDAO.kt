package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.Message

@Dao
interface MessageDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: Message)

    @Query("SELECT * FROM messages WHERE id = :messageId")
    suspend fun getMessageById(messageId: String): Message?

    @Query("SELECT * FROM messages WHERE (sender = :userId1 AND receiver = :userId2) OR (sender = :userId2 AND receiver = :userId1) ORDER BY sentTime ASC")
    suspend fun getMessagesByUserIds(userId1: String, userId2: String): List<Message>
}