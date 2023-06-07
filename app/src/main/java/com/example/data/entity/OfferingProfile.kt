package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offeringProfiles")
data class OfferingProfile(
    @PrimaryKey val id: String,
    val userId: String,
    val description: String
)