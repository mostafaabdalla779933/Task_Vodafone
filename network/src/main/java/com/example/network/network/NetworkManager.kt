package com.evaph.network.network


import android.util.Log
import com.example.network.model.AirLineModel
import com.example.network.network.INetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject



class NetworkManager @Inject constructor(val apiService : APIService) : INetworkManager {

    override suspend fun  getRequest(
        api: String,
    ): Response<List<AirLineModel>> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getRequest(api)
            response
        }
    }
}