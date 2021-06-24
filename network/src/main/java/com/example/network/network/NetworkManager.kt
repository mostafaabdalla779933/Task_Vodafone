package com.evaph.network.network


import android.util.Log
import com.evaph.network.model.response.BaseResponse
import com.example.network.model.AirLineModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject

class NetworkManager @Inject constructor() {

    @Inject
    lateinit var apiService : APIService


    suspend fun  getRequest(
        api: String,
    ): Response<List<AirLineModel>> {
        Log.i("main", "getRequest: " + api)
        return withContext(Dispatchers.IO) {
            val response = apiService.getArrRequest(api)
            response
        }
    }
}