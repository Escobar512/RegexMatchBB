package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.OfferingProfile

@Dao
interface OfferingProfileDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(offeringProfile: OfferingProfile)

    @Query("SELECT * FROM offeringProfiles WHERE id = :offeringProfileId")
    suspend fun getOfferingProfileById(offeringProfileId: String): OfferingProfile?

    @Query("SELECT * FROM offeringProfiles WHERE userId = :userId")
    suspend fun getOfferingProfileByUserId(userId: String): OfferingProfile?
}