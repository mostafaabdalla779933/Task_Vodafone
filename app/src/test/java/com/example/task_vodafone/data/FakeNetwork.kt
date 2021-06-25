package com.example.task_vodafone.data

import com.example.network.model.AirLineModel
import com.example.network.network.INetworkManager
import retrofit2.Response

class FakeNetwork : INetworkManager {


    override suspend fun getRequest(api: String): Response<List<AirLineModel>> {
        TODO("Not yet implemented")
    }
}