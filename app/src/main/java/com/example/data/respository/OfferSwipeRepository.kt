package com.example.data.respository

import com.example.data.dao.OfferSwipeDAO
import com.example.data.entity.OfferSwipe

class OfferSwipeRepository(private val offerSwipeDao: OfferSwipeDAO) {
    suspend fun insert(offerSwipe: OfferSwipe) {
        offerSwipeDao.insert(offerSwipe)
    }

    suspend fun getOfferSwipeById(offerSwipeId: String): OfferSwipe? {
        return offerSwipeDao.getOfferSwipeById(offerSwipeId)
    }

}