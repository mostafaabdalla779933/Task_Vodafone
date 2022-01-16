package com.example.task_vodafone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.entity.AirLineEntity
import com.example.task_vodafone.repo.ILocalRepo

class FakeLocalRepo :ILocalRepo {


    override suspend fun cachAirlines(airlines: List<AirLineEntity>) {

    }

    override suspend fun getAirlines(): List<AirLineEntity> {
        return listOf(AirLineEntity("1","",""))
    }

    override suspend fun addAirline(airLineEntity: AirLineEntity) {

    }

    override fun putCached() {
        TODO("Not yet implemented")
    }

    override fun getCached(): Boolean {
        TODO("Not yet implemented")
    }
}