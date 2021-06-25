package com.example.task_vodafone

import com.example.entity.AirLineEntity
import com.example.task_vodafone.repo.ILocalRepo

class FakeLocalRepo :ILocalRepo {


    override suspend fun cachAirlines(airlines: List<AirLineEntity>) {

    }

    override suspend fun getAirlines(): List<AirLineEntity> {
        return listOf(AirLineEntity("1","",""))
    }
}