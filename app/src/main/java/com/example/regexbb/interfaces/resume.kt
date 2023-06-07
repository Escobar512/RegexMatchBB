package com.example.regexbb.interfaces

import com.example.regexbb.models.Resume
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface resume {
    @GET("resumesEndpoint")
    suspend fun getAllResumes(): Response<List<Resume>>

    @POST("resumesEndpoint")
    suspend fun createResume(@Body resume: Resume): Response<Resume>

    @GET("resumesEndpoint/{profileId}")
    suspend fun getResumeLooker(@Path("profileId") profileId: String): Response<Resume>

    @DELETE("resumesEndpoint/{profileId}")
    suspend fun deleteResume(@Path("profileId") profileId: String): Response<Unit>
}