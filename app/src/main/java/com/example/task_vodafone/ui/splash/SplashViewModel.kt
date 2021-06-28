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

    // call api and call function to cach the response
    fun getAirLines(){
            CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
                val response = remoteRepo.getAirLines()
                handleError(response.code())
                if (response.isSuccessful) {
                    cachAirlines(response.body())
                    delay(2000)
                    dataCached.postValue(true)
                }else{
                    errorLineLiveData.postValue(true)
                }
            }
    }

    // cach the response  in the room
    fun cachAirlines(airlines : List<AirLineModel>?){
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler ).launch {
            airlines?.let {
                val list = AirLineEntity.toEntityList(it)
                localRepo.cachAirlines(list)

            }
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


}

