package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String,
    val email: String,
    val userName: String,
    val password: String,
    val isLooking: Boolean
)