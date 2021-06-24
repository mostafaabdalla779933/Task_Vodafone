package com.example.task_vodafone.repo


import com.evaph.network.network.Endpoints
import com.evaph.network.network.NetworkManager
import com.example.network.model.AirLineModel
import retrofit2.Response
import javax.inject.Inject

class RemoteRepo @Inject constructor() : IRemoteRepo {
    @Inject
    lateinit var networkManager: NetworkManager



    override suspend fun getAirLines():Response<List<AirLineModel>>{
        val response = networkManager.getRequest(Endpoints.AIRLINES)
        return response
    }

}
