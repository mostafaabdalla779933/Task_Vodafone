package com.example.task_vodafone.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.db.AirlLineDao
import com.example.entity.AirLineEntity
import javax.inject.Inject

class LocalRepo @Inject constructor(val airlLineDao : AirlLineDao) : ILocalRepo {

    // cach the list of Airlines in the room
    override suspend fun cachAirlines(airlines: List<AirLineEntity>){
        airlLineDao.insertAirlLines(airlines)
    }
    // get the list of Airlines from the room
    override suspend fun getAirlines() : LiveData<List<AirLineEntity>> = airlLineDao.getAirlLines()

    // add Airline to the room
    override suspend fun addAirline(airLineEntity: AirLineEntity) {
        airlLineDao.insertAirlLine(airLineEntity)
    }


}

