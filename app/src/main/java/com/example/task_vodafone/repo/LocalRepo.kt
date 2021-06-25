package com.example.task_vodafone.repo

import com.evaph.database.db.AirlLineDao
import com.example.entity.AirLineEntity
import javax.inject.Inject

class LocalRepo @Inject constructor(val airlLineDao : AirlLineDao) : ILocalRepo {



    // cach the list of Airlines in the room
    override suspend fun cachAirlines(airlines: List<AirLineEntity>) {
        airlLineDao.insertAirlLines(airlines)
    }

    // get the list of Airlines from the room
    override suspend fun getAirlines() = airlLineDao.getAirlLines()


}

