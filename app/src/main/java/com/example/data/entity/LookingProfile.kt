package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lookingProfiles")
data class LookingProfile(
    @PrimaryKey val id: String,
    val userId: String,
    val description: String,
    val resumeUrl: String,
    val age: Int,
    val degree: String,
    val school: String,
    val position: String
)