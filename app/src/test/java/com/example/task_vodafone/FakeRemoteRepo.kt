package com.example.task_vodafone


import com.example.network.model.AirLineModel
import com.example.task_vodafone.repo.IRemoteRepo

import retrofit2.Response
import retrofit2.Retrofit


class FakeRemoteRepo : IRemoteRepo{
    override suspend fun getAirLines(): Response<List<AirLineModel>> {
        TODO("Not yet implemented")
    }
}