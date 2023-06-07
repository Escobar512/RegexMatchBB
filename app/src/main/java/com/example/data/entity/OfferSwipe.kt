package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offerSwipe")
data class OfferSwipe(
    @PrimaryKey val id: String,
    val lookerId: String,
    val offerId: String,
)