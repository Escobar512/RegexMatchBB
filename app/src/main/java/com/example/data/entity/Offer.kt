package com.example.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offers")
data class Offer(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String,
    val timeStamp: Long,
    val pay: Double,
    val location: String,
    val schedule: String,
    val mode: String,
    val requirements: String,
    val idOfferor: Long
)
