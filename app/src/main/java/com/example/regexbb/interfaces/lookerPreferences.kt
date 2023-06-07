package com.example.regexbb.interfaces

import com.example.regexbb.models.LookerPreferences
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface lookerPreferences {
    @GET("lookerPreferencesEndpoint")
    suspend fun getAllLookerPreferences(): Response<List<LookerPreferences>>

    @POST("lookerPreferencesEndpoint")
    suspend fun createLookerPreferences(@Body lookerPreferences: LookerPreferences): Response<LookerPreferences>

    @PUT("lookerPreferencesEndpoint/{lookerId}")
    suspend fun updateLookerPreferences(@Path("lookerId") lookerId: String, @Body lookerPreferences: LookerPreferences): Response<LookerPreferences>
}
