package com.example.data.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.*
import com.example.data.entity.*


@Database(
    entities = [User::class,
        Technology::class,
        OfferSwipe::class,
        OfferingProfile::class,
        Offer::class,
        Message::class,
        LookingProfile::class,
        Chat::class],
    version = 1
)


abstract class RegexDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun technologyDAO(): TechnologyDAO
    abstract fun offerSwipeDAO(): OfferSwipeDAO
    abstract fun offeringProfileDAO(): OfferingProfileDAO
    abstract fun offerDAO(): OfferDAO
    abstract fun messageDAO(): MessageDAO
    abstract fun lookingProfileDAO(): LookingProfileDAO
    abstract fun chatDAO(): ChatDAO
}