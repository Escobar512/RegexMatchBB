package com.example.data.respository

import com.example.data.dao.LookingProfileDAO
import com.example.data.entity.LookingProfile

class LookingProfileRepository(private val lookingProfileDao: LookingProfileDAO) {
    suspend fun insert(lookingProfile: LookingProfile) {
        lookingProfileDao.insert(lookingProfile)
    }

    suspend fun getLookingProfileById(lookingProfileId: String): LookingProfile? {
        return lookingProfileDao.getLookingProfileById(lookingProfileId)
    }

    suspend fun getLookingProfileByUserId(userId: String): LookingProfile? {
        return lookingProfileDao.getLookingProfileByUserId(userId)
    }
}