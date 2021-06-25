package com.evaph.network.network

import com.example.network.model.AirLineModel
import com.google.gson.JsonElement
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface APIService {


    @GET
    suspend fun getRequest(
        @Url api: String,
    ): Response<List<AirLineModel>>

    @POST
    @JvmSuppressWildcards
    suspend fun postRequest(
        @Url api: String,
        @HeaderMap headers: Map<String, String>?,
        @Body body: Map<String, Any>?
    ): Response<JsonElement>

    @DELETE
    suspend fun deleteRequest(
        @Url api: String,
        @HeaderMap headers: Map<String, String>?,
        @QueryMap param: Map<String, Any>?
    ): Response<JsonElement>


}