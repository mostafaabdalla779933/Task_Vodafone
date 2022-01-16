package com.example.task_vodafone.data

import com.example.entity.AirLineEntity
import com.example.network.model.AirLineModel
import com.example.network.network.INetworkManager
import retrofit2.Response

class FakeNetwork : INetworkManager {


    override suspend fun getRequest(api: String): Response<List<AirLineModel>> {

      return  Response.success(200, listOf(AirLineModel("","","")))

    }
}