package com.example.task_vodafone.repo

import com.example.entity.AirLineEntity



interface ILocalRepo  {

    suspend fun cachAirlines(airlines: List<AirLineEntity>)

    suspend fun getAirlines(): List<AirLineEntity>

}