package com.example.task_vodafone.repo

import com.evaph.network.network.Endpoints
import com.example.network.model.AirLineModel
import retrofit2.Response

interface IRemoteRepo{

    suspend fun getAirLines(): Response<List<AirLineModel>>
}