package com.example.task_vodafone

import android.util.Log
import com.evaph.network.network.Endpoints
import com.evaph.network.network.NetworkManager
import com.example.network.model.AirLineModel
import com.example.network.model.response.AirLineResponse
import javax.inject.Inject

class CountryRepo @Inject constructor() {
    @Inject
    lateinit var networkManager: NetworkManager

    suspend fun getCountries():AirLineModel? {

       val response = networkManager.getRequest("${Endpoints.AIRLINES}56")

            if(response.isSuccessful){
                Log.i("main", "getCountries: ")
                return response.body()
            }else{
                Log.i("main", "getCountries: ")
                return null

            }

        }

}
