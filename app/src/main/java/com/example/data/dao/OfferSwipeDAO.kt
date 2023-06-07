package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.OfferSwipe

@Dao
interface OfferSwipeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(offerSwipe: OfferSwipe)

    @Query("SELECT * FROM offerSwipe WHERE id = :offerSwipeId")
    suspend fun getOfferSwipeById(offerSwipeId: String): OfferSwipe?

}