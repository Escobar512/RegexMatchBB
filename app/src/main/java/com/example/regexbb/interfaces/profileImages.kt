package com.example.regexbb.interfaces

import com.example.regexbb.models.ProfileImages
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface profileImages {
    @GET("profileImagesEndpoint")
    suspend fun getAllProfileImages(): Response<List<ProfileImages>>

    @POST("profileImagesEndpoint")
    suspend fun createProfileImage(@Body profileImage: ProfileImages): Response<ProfileImages>

    @GET("profileImagesEndpoint/looker/{profileId}")
    suspend fun getProfileImagesLooker(@Path("profileId") profileId: String): Response<List<ProfileImages>>

    @GET("profileImagesEndpoint/offeror/{profileId}")
    suspend fun getProfileImagesOfferor(@Path("profileId") profileId: String): Response<List<ProfileImages>>

    @DELETE("profileImagesEndpoint/{profileId}")
    suspend fun deleteProfileImage(@Path("profileId") profileId: String): Response<Unit>
}
