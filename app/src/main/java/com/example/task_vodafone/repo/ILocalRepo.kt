package com.example.task_vodafone.repo

import androidx.lifecycle.LiveData
import com.example.entity.AirLineEntity



interface ILocalRepo  {

    suspend fun cachAirlines(airlines: List<AirLineEntity>)

    suspend fun getAirlines(): LiveData<List<AirLineEntity>>

    suspend fun addAirline(airLineEntity: AirLineEntity)

}