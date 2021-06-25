package com.example.network.network

import com.example.network.model.AirLineModel
import retrofit2.Response

interface INetworkManager {
    suspend fun  getRequest(
        api: String,
    ): Response<List<AirLineModel>>
}