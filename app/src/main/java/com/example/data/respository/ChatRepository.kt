package com.example.data.respository

import com.example.data.dao.ChatDAO
import com.example.data.entity.Chat

class ChatRepository(private val chatDao: ChatDAO) {
    suspend fun insert(chat: Chat) {
        chatDao.insert(chat)
    }

    suspend fun getChatById(chatId: String): Chat? {
        return chatDao.getChatById(chatId)
    }

    suspend fun getChatByUsers(lookerId: String, offererId: String): Chat? {
        return chatDao.getChatByUsers(lookerId, offererId)
    }
}