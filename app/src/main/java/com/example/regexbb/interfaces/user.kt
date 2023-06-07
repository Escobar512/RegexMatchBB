package com.example.regexbb.interfaces

import com.example.regexbb.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface user {
    @GET("userEndpoint")
    suspend fun getAllUsers(): Response<List<User>>

    @POST("userEndpoint")
    suspend fun createUser(@Body user: User): Response<User>
}