package com.example.task_vodafone.ui.splash

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

    fun getAirLines(): LiveData<Boolean> {
        val mutableLiveData = MutableLiveData<Boolean>()

        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler ).launch {
            val response =  remoteRepo.getAirLines()
            handleError(response.code())
            if(response.isSuccessful){
                cachAirlines(response.body()?.take(1000))
                delay(2000)
                mutableLiveData.postValue(true)
            }
        }
        return mutableLiveData
    }

    fun cachAirlines(airlines : List<AirLineModel>?){
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler ).launch {
            airlines?.let {
                val list = AirLineEntity.toEntityList(it)
                localRepo.cachAirlines(list)

            }
        }
    }

    fun handleError(code : Int){
        if(code in 200..399){

            stateLiveData.postValue("connection success")

        } else if (code in 400..499){

            // client error
            stateLiveData.postValue("connection faild")

        }else if (code >= 500 ){

            stateLiveData.postValue("server problem")

        }
    }

    val coroutineExceptionHandler= CoroutineExceptionHandler{ context , thro ->
    }



}

