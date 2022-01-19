package com.example.task_vodafone.repo

import android.content.SharedPreferences
import com.example.db.AirlLineDao
import com.example.entity.AirLineEntity
import javax.inject.Inject

class LocalRepo @Inject constructor(val airlLineDao : AirlLineDao,val shared : SharedPreferences) : ILocalRepo {

    // cach the list of Airlines in the room
    override suspend fun cachAirlines(airlines: List<AirLineEntity>){
        airlLineDao.insertAirlLines(airlines)
    }
    // get the list of Airlines from the room
    override suspend fun getAirlines() : List<AirLineEntity> = airlLineDao.getAirlLines()

    // add Airline to the room
    override suspend fun addAirline(airLineEntity: AirLineEntity) {
        airlLineDao.insertAirlLine(airLineEntity)
    }



    override fun putCached() {
        shared.edit().putBoolean("cached",true).apply()
    }

    override fun getCached(): Boolean = shared.getBoolean("cached",false)



}

