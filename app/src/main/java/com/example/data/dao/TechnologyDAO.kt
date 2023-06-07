package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.Technology

@Dao
interface TechnologyDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(technology: Technology)

    @Query("SELECT * FROM technologies WHERE id = :technologyId")
    suspend fun getTechnologyById(technologyId: String): Technology?

    @Query("SELECT * FROM technologies WHERE objectId = :objectId")
    suspend fun getTechnologyByObjectId(objectId: String): Technology?
}