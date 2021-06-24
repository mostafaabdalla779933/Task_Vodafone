package com.evaph.network.network


import com.example.network.model.AirLineModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class NetworkManager @Inject constructor() {

    @Inject
    lateinit var apiService : APIService


    suspend fun  getRequest(
        api: String,
    ): Response<List<AirLineModel>> {

        return withContext(Dispatchers.IO) {
            val response = apiService.getArrRequest(api)
            response
        }
    }
}