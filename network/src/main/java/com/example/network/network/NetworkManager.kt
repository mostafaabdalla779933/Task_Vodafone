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


    private val headers: MutableMap<String, String> =
        object : HashMap<String, String>() {
            init {
                put("Accept", "application/json")
            }
        }

//    @Inject
//    lateinit var userPref: UserPref

//    private val headers: MutableMap<String, String> =
//        object : HashMap<String, String>() {
//            init {
//                put("Accept", "application/json")
//            }
//        }

//    private fun updateHeaders() {
//        val accessToken: String? = userPref.getAccessToken()
//        if (accessToken != null) headers["Authorization"] = "Bearer $accessToken"
//        else headers.remove("Authorization")
//    }


    suspend fun  <T>getRequest(
        api: String,
        parseClass: Class<T>,
    ): Pair<T?,Int> {
        Log.i("main", "getRequest: " + api)
        return withContext(Dispatchers.IO) {
            val response = apiService.getRequest(api)
            val parse =parseResponse(response,parseClass )


            Pair(parse,response.code())
        }
    }


    suspend fun  getArrRequest(
        api: String,
    ): Response<List<AirLineModel>> {
        Log.i("main", "getRequest: " + api)
        return withContext(Dispatchers.IO) {
            val response = apiService.getArrRequest(api)
            response
        }
    }


//    suspend fun <T> getRequest(
//        api: String,
//        headers: Map<String, String>?,
//        param: Map<String, Any> = HashMap(),
//    ): Response<T> {
//        return withContext(Dispatchers.IO) {
//            aPIService .getRequest(api,headers,param)
//
//        }
//    }

//    suspend fun <T> postRequest(
//        api: String,
//        body: Map<String, Any> = HashMap(),
//        parseClass: Class<T>,
//    ): Response<T> {
//       // updateHeaders()
//        return withContext(Dispatchers.IO) {
//            parseResponse(
//                retrofit.create(APIService::class.java).postRequest(api, headers, body), parseClass
//            )
//        }
//    }

//    suspend fun <T> putRequest(
//        api: String,
//        body: Map<String, Any> = HashMap(),
//        parseClass: Class<T>,
//    ): BaseResponse<T> {
//        updateHeaders()
//        return withContext(Dispatchers.IO) {
//            parseResponse(
//                retrofit.create(APIService::class.java).putRequest(api, headers, body), parseClass
//            )
//        }
//    }

//    suspend fun <T> deleteRequest(
//        api: String,
//        param: Map<String, Any> = HashMap(),
//        parseClass: Class<T>,
//    ): BaseResponse<T> {
//        updateHeaders()
//        return withContext(Dispatchers.IO) {
//            parseResponse(
//                retrofit.create(APIService::class.java).deleteRequest(api, headers, param),
//                parseClass
//            )
//        }
//    }



    @Throws(
        IOException::class,
        InstantiationException::class,
        IllegalAccessException::class,
        JSONException::class
    )
    private fun <T> parseResponse(
        response: Response<JsonElement>,
        parseClass: Class<T>,
    ): T? {
        return try {
            if (!response.isSuccessful) {
                null
            } else {
                if (response.body()?.isJsonArray!!){
                 Gson().fromJson(response.body(),parseClass)
                }else{

                Gson().fromJson(response.body(),parseClass)
                }

            }
        } catch (e: Exception) {
            throw e
        }
    }
}