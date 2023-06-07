package com.example.regexbb.interfaces

import com.example.regexbb.models.Message
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface messages {
    @GET("messagesEndpoint")
    suspend fun getAllMessages(): Response<List<Message>>

    @POST("messagesEndpoint")
    suspend fun createMessage(@Body message: Message): Response<Message>
}