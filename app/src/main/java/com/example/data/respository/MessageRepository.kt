package com.example.data.respository

import com.example.data.dao.MessageDAO
import com.example.data.entity.Message

class MessageRepository(private val messageDao: MessageDAO) {
    suspend fun insert(message: List<Message>) {
        messageDao.insertMessages(message)
    }

    suspend fun getMessagesByChatId(chatId: String): List<Message>? {
        return messageDao.getMessagesByChatId(chatId)
    }
}