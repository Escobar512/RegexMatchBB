package com.example.data.respository

import com.example.data.dao.OfferingProfileDAO
import com.example.data.entity.OfferingProfile

class OfferingProfileRepository(private val offeringProfileDao: OfferingProfileDAO) {
    suspend fun insert(offeringProfile: OfferingProfile) {
        offeringProfileDao.insert(offeringProfile)
    }

    suspend fun getOfferingProfileById(offeringProfileId: String): OfferingProfile? {
        return offeringProfileDao.getOfferingProfileById(offeringProfileId)
    }

    suspend fun getOfferingProfileByUserId(userId: String): OfferingProfile? {
        return offeringProfileDao.getOfferingProfileByUserId(userId)
    }
}