package com.example.task_vodafone.repo


import android.util.Log
import com.evaph.network.network.Endpoints
import com.evaph.network.network.NetworkManager
import com.example.network.model.AirLineModel
import com.example.network.network.INetworkManager
import dagger.Provides
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton



class RemoteRepo @Inject constructor(val networkManager: INetworkManager ) : IRemoteRepo {

    override suspend fun getAirLines():Response<List<AirLineModel>>{
        Log.i("main", "getAirLines: 2")
        val response = networkManager.getRequest(Endpoints.AIRLINES)
        return response
    }

}
