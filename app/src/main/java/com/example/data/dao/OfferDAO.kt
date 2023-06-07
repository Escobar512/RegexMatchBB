package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.Offer

@Dao
interface OfferDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(offer: Offer)

    @Query("SELECT * FROM offers WHERE id = :offerId")
    suspend fun getOfferById(offerId: Long): Offer?

    @Query("SELECT * FROM offers WHERE idOfferor = :offerorId")
    suspend fun getOffersByOfferorId(offerorId: Long): List<Offer>

    @Query("SELECT * FROM offers WHERE timeStamp >= :startTimeStamp AND timeStamp <= :endTimeStamp")
    suspend fun getOffersByTimestamp(startTimeStamp: Long, endTimeStamp: Long): List<Offer>
}