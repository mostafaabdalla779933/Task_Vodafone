package com.evaph.network.network


import android.util.Log
import com.example.network.model.AirLineModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
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


    suspend fun  getRequest(
        api: String,
    ): Response<AirLineModel> {
        Log.i("main", "getRequest: " + api)
        return withContext(Dispatchers.IO) {
            apiService.getRequest(api)

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



//    @Throws(
//        IOException::class,
//        InstantiationException::class,
//        IllegalAccessException::class,
//        JSONException::class
//    )
//    private fun <T> parseResponse(
//        response: Response<JsonElement>,
//    ): Response<T> {
//        return try {
//            when(response.code()){
//                400 ->{Response<T>()?.apply {
//                    if (response.body()!!.asJsonObject.has("message"))
//                        message = response.body()!!.asJsonObject["message"].asString
//                    data = gson.fromJson(response.body()!!.asJsonObject["data"], parseClass)
//                }}
//
//
//            }
//        } catch (e: Exception) {
//            throw e
//        }
//    }
}