package com.example.regexbb.interfaces

import com.example.regexbb.models.OfferingProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface offeringProfile {
    @GET("offeringProfilesEndpoint")
    suspend fun getAllOfferingProfiles(): Response<List<OfferingProfile>>

    @POST("offeringProfilesEndpoint")
    suspend fun createOfferingProfile(@Body offeringProfile: OfferingProfile): Response<OfferingProfile>

    @PUT("offeringProfilesEndpoint/{profileId}")
    suspend fun updateOfferingProfile(@Path("profileId") profileId: String, @Body offeringProfile: OfferingProfile): Response<OfferingProfile>
}
