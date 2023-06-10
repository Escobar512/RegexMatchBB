package com.example.regexbb.interfaces

import com.example.regexbb.models.Message
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface messages {
    @GET("messagesEndpoint")
    suspend fun getAllMessages(): Response<List<Message>>

    @GET("messageEndpoint/{chatId}")
    suspend fun getMessagesChat(@Path("chatId") chatId : String): Response<List<Message>>

    @POST("messageEndpoint")
    suspend fun createMessage(@Body message: Message): Response<Message>
}