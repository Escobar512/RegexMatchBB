package com.example.regexbb.interfaces

import com.example.regexbb.models.OfferingProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface offeringProfile {
    @GET("offeringProfileEndpoint")
    suspend fun getAllOfferingProfiles(): Response<List<OfferingProfile>>

    @GET("offeringProfileEndpoint/user/{userId}")
    suspend fun getOfferingProfileByUserId(@Path("userId") userId: String): Response<OfferingProfile>

    @POST("offeringProfileEndpoint")
    suspend fun createOfferingProfile(@Body offeringProfile: OfferingProfile): Response<OfferingProfile>

    @PUT("offeringProfileEndpoint/{profileId}")
    suspend fun updateOfferingProfile(@Path("profileId") profileId: String, @Body offeringProfile: OfferingProfile): Response<OfferingProfile>
}
