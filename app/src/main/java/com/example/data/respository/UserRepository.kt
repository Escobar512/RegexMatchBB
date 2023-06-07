package com.example.data.respository

import com.example.data.dao.UserDAO
import com.example.data.entity.User

class UserRepository(private val userDao: UserDAO) {
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun getUserById(userId: String): User? {
        return userDao.getUserById(userId)
    }

}