package com.example.task_vodafone.ui.splash

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.entity.AirLineEntity
import com.example.network.model.AirLineModel
import com.example.task_vodafone.repo.ILocalRepo
import com.example.task_vodafone.repo.IRemoteRepo
import com.example.task_vodafone.repo.LocalRepo
import com.example.task_vodafone.repo.RemoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(val remoteRepo: IRemoteRepo, val localRepo : ILocalRepo): ViewModel() {

    var stateLiveData =  MutableLiveData<String?>()
    var errorLineLiveData = MutableLiveData<Boolean>()
    var dataCached = MutableLiveData<Boolean>()
    var job : Job? = null

    // call api and call function to cach the response
    fun getAirLines(){
        job =  CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
                if(!localRepo.getCached()) {
                    val response = remoteRepo.getAirLines()
                    handleError(response.code())
                    if (response.isSuccessful) {
                        cachAirlines(response.body())
                        localRepo.putCached()
                        dataCached.postValue(true)
                    } else {
                        errorLineLiveData.postValue(true)
                    }
                }else{
                    delay(1000)
                    dataCached.postValue(true)
                }
            }
    }

    // cach the response  in the room
    suspend fun cachAirlines(airlines : List<AirLineModel>?){
            airlines?.let {
                val list = AirLineEntity.toEntityList(it)
                localRepo.cachAirlines(list)
            }
    }


    // handle api response code
    fun handleError(code : Int){
        when {
            code in 200..399 -> {
                stateLiveData.postValue("connection success")
            }
            code in 400..499 -> {
                // client error
                errorLineLiveData.postValue(true)
                stateLiveData.postValue("connection faild")
            }
            code >= 500 -> {
                errorLineLiveData.postValue(true)
                stateLiveData.postValue("server problem")
            }
        }
    }

    val coroutineExceptionHandler= CoroutineExceptionHandler{ _, thro->
        errorLineLiveData.postValue(true)
    }


    fun clear(){
        job?.cancel()
    }

}

