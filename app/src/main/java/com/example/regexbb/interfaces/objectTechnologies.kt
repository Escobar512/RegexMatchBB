package com.example.regexbb.interfaces

import com.example.regexbb.models.ObjectTechnologies
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface objectTechnologies {
    @GET("objectTechnologiesEndpoint")
    suspend fun getAllObjectTechnologies(): Response<List<ObjectTechnologies>>

    @GET("objectTechnologiesEndpoint/technologies/{dObjectId}")
    suspend fun getTechObj(@Path("dObjectId") dObjectId: String): Response<List<ObjectTechnologies>>

    @POST("objectTechnologiesEndpoint")
    suspend fun createObjectTechnologies(@Body objectTechnologies: ObjectTechnologies): Response<ObjectTechnologies>
}