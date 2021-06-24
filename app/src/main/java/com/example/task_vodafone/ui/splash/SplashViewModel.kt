package com.example.task_vodafone.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.entity.AirLineEntity
import com.example.network.model.AirLineModel
import com.example.task_vodafone.repo.LocalRepo
import com.example.task_vodafone.repo.RemoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(val remoteRepo: RemoteRepo,val localRepo : LocalRepo ): ViewModel() {


    fun getAirLines(): LiveData<Boolean> {
        val mutableLiveData = MutableLiveData<Boolean>()

        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler ).launch {
            val response =  remoteRepo.getAirLines()
            handleError(response.code())
            if(response.isSuccessful){
                cachAirlines(response.body()?.take(50))
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
        when(code){
            400 ->{ Log.i("main", "handleError: ")}
        }
    }

    val coroutineExceptionHandler= CoroutineExceptionHandler{ context , thro ->
    }



}

