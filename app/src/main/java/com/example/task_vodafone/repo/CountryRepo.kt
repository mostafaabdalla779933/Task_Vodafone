package com.example.task_vodafone.repo


import com.evaph.network.network.Endpoints
import com.evaph.network.network.NetworkManager
import com.example.network.model.AirLineModel
import com.example.network.model.response.AirLineResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import javax.inject.Inject

class CountryRepo @Inject constructor() {
    @Inject
    lateinit var networkManager: NetworkManager

    suspend fun getCountries():Pair<AirLineModel?,Int> {
       val response = networkManager.getRequest("${Endpoints.AIRLINES}56",AirLineModel::class.java)
       return response
    }

    suspend fun getAirLines():Response<List<AirLineModel>>{
        val response = networkManager.getArrRequest(Endpoints.AIRLINES)
        return response
    }



}
