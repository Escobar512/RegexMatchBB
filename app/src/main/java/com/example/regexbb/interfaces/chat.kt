package com.example.regexbb.interfaces

import com.example.regexbb.models.Chat
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface chat {
    @GET("chatsEndpoint")
    suspend fun getAllChats(): Response<List<Chat>>

    @POST("chatsEndpoint")
    suspend fun createChat(@Body chat: Chat): Response<Chat>

    @GET("chatsEndpoint/looker/{lookerId}")
    suspend fun getChatsByLookerId(@Path("lookerId") lookerId: String): Response<List<Chat>>

    @GET("chatsEndpoint/offerer/{offererId}")
    suspend fun getChatsByOffererId(@Path("offererId") offererId: String): Response<List<Chat>>
}