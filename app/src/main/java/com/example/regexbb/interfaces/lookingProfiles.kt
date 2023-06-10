package com.example.regexbb.interfaces

import com.example.regexbb.models.LookingProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface lookingProfiles {
    @GET("lookingProfileEndpoint")
    suspend fun getAllLookingProfiles(): Response<List<LookingProfile>>

    @GET("lookingProfileEndpoint/{profileId}")
    suspend fun getLookingProfile(@Path("profileId") profileId: String): Response<LookingProfile>

    @GET("lookingProfileEndpoint/user/{userId}")
    suspend fun getLookingProfileByUser(@Path("userId") userId: String): Response<LookingProfile>

    @GET("lookingProfileEndpoint/offer/{offerId}")
    suspend fun getLookersMatched(@Path("offerId") offerId: String): Response<List<LookingProfile>>

    @POST("lookingProfileEndpoint")
    suspend fun createLookingProfile(@Body lookingProfile: LookingProfile): Response<LookingProfile>

    @PUT("lookingProfileEndpoint/{profileId}")
    suspend fun updateLookingProfile(@Path("profileId") profileId: String, @Body lookingProfile: LookingProfile): Response<LookingProfile>
}