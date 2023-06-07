package com.example.data.respository

import com.example.data.dao.OfferDAO
import com.example.data.entity.Offer

class OfferRepository(private val offerDao: OfferDAO) {
    suspend fun insert(offer: Offer) {
        offerDao.insert(offer)
    }

    suspend fun getOfferById(offerId: Long): Offer? {
        return offerDao.getOfferById(offerId)
    }

    suspend fun getOffersByOfferorId(offerorId: Long): List<Offer> {
        return offerDao.getOffersByOfferorId(offerorId)
    }

    suspend fun getOffersByTimestamp(startTimeStamp: Long, endTimeStamp: Long): List<Offer> {
        return offerDao.getOffersByTimestamp(startTimeStamp, endTimeStamp)
    }
}