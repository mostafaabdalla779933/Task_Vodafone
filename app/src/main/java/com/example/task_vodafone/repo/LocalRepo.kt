package com.example.task_vodafone.repo

import com.evaph.database.db.AirlLineDao
import com.example.entity.AirLineEntity
import javax.inject.Inject

class LocalRepo @Inject constructor() : ILocalRepo {
    @Inject
    lateinit var airlLineDao : AirlLineDao

    override suspend fun cachAirlines(airlines: List<AirLineEntity>) {
        airlLineDao.insertAirlLines(airlines)
    }

    override suspend fun getAirlines() = airlLineDao.getAirlLines()


}

