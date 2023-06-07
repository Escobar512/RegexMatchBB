package com.example.data.respository

import com.example.data.entity.Technology
import com.example.data.dao.TechnologyDAO

class TechnologyRepository(private val technologyDao: TechnologyDAO) {
    suspend fun insert(technology: Technology) {
        technologyDao.insert(technology)
    }

    suspend fun getTechnologyById(technologyId: String): Technology? {
        return technologyDao.getTechnologyById(technologyId)
    }

    suspend fun getTechnologyByObjectId(objectId: String): Technology? {
        return technologyDao.getTechnologyByObjectId(objectId)
    }
}