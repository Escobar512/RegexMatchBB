package com.example.regexbb.interfaces

import com.example.regexbb.models.Technology
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface technologies {
    @GET("technologiesEndpoint")
    suspend fun getAllTechnologies(): Response<List<Technology>>

    @POST("technologiesEndpoint")
    suspend fun createTechnology(@Body technology: Technology): Response<Technology>
}
