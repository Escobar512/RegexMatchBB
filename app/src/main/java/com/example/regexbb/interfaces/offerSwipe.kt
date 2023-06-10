package com.example.regexbb.interfaces

import com.example.regexbb.models.OfferSwipe
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface  offerSwipe {
    @GET("offerSwipeEndpoint")
    suspend fun getAllOfferSwipes(): Response<List<OfferSwipe>>

    @PUT("offerSwipeEndpoint")
    suspend fun updateOfferSwipe(@Body offerSwipe: OfferSwipe): Response<OfferSwipe>

    @POST("offerSwipeEndpoint")
    suspend fun createOfferSwipe(@Body offerSwipe: OfferSwipe): Response<OfferSwipe>
}