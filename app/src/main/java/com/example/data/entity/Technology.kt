package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "technologies")
data class Technology(
    @PrimaryKey val id: String,
    val objectId: String,
    val name: String,
    val yearsOfExperience: Int
)