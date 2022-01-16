package com.example.task_vodafone


import com.example.network.model.AirLineModel
import com.example.task_vodafone.repo.IRemoteRepo
import retrofit2.Response



class FakeRemoteRepo : IRemoteRepo{
    override suspend fun getAirLines(): Response<List<AirLineModel>> {

        return  Response.success(200, listOf(AirLineModel("","","")))
    }
}