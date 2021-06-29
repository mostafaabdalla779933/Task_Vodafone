package com.example.task_vodafone.data

import com.example.network.model.AirLineModel
import com.example.network.network.INetworkManager
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

class FakeNetwork : INetworkManager {


    override suspend fun getRequest(api: String): Response<List<AirLineModel>> {
        TODO("Not yet implemented")
//       okhttp3.Response.Builder()
//            .code(200)
//            .message("")
//            .request(RequestBody.create())
//            .protocol(Protocol.HTTP_1_0)
//            .body(ResponseBody.create())
//           // .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
//            .addHeader("content-type", "application/json")
//            .build()
    }
}