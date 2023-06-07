package com.example.regexbb.interfaces

import com.example.regexbb.models.Offer
import com.example.regexbb.models.LookingProfile
import com.example.regexbb.models.ObjectTechnologies
import com.example.regexbb.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface offer {
    @GET("offerEndpoint")
    suspend fun getAllOffers(): Response<List<Offer>>

    @POST("offerEndpoint")
    suspend fun createOffer(@Body offer: Offer): Response<Offer>

    @GET("objectTechnologiesEndpoint/technologyMatches/{dObjectId}")
    suspend fun getOffersMatched(@Path("dObjectId") dObjectId: String): Response<List<Offer>>

    @GET("offerEndpoint/offersMatched/{dObjectId}")
    suspend fun getOffersReMatched(@Path("dObjectId") dObjectId: String): Response<List<Offer>>

    @GET("offerEndpoint/{idOfferor}")
    suspend fun getOffersByOfferor(@Path("idOfferor") idOfferor: String): Response<List<Offer>>

    @GET("offerEndpoint/lookers")
    suspend fun getLookingProfilesByOffer(@Query("offerorId") offerorId: String): Response<List<LookingProfile>>
}