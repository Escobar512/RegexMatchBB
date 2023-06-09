package com.example.regexbb.interfaces

import com.example.regexbb.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface user {
    @GET("userEndpoint")
    suspend fun getAllUsers(): Response<List<User>>

    @GET("userEndpoint/login/{userName}/{password}")
    suspend fun getLogin(@Path("userName") userName: String, @Path("password") password: String): Response<User>

    @POST("userEndpoint")
    suspend fun createUser(@Body user: User): Response<User>
}