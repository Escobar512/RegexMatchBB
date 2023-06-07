package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.LookingProfile


@Dao
interface LookingProfileDAO  {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lookingProfile: LookingProfile)

    @Query("SELECT * FROM lookingProfiles WHERE id = :lookingProfileId")
    suspend fun getLookingProfileById(lookingProfileId: String): LookingProfile?

    @Query("SELECT * FROM lookingProfiles")
    fun getAllLookingProfiles(): LookingProfile?

    @Query("SELECT * FROM lookingProfiles WHERE userId = :userId")
    suspend fun getLookingProfileByUserId(userId: String): LookingProfile?
}