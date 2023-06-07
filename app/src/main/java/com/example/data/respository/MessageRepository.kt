package com.example.data.respository

import com.example.data.dao.MessageDAO
import com.example.data.entity.Message

class MessageRepository(private val messageDao: MessageDAO) {
    suspend fun insert(message: Message) {
        messageDao.insert(message)
    }

    suspend fun getMessageById(messageId: String): Message? {
        return messageDao.getMessageById(messageId)
    }

    suspend fun getMessagesByUserIds(userId1: String, userId2: String): List<Message> {
        return messageDao.getMessagesByUserIds(userId1, userId2)
    }
}