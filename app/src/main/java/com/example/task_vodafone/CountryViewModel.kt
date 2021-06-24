package com.example.task_vodafone

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.AirLineModel
import com.example.network.model.response.AirLineResponse
import com.example.task_vodafone.repo.CountryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class CountryViewModel  @Inject constructor(val repo: CountryRepo): ViewModel(){

    val coroutineExceptionHandler= CoroutineExceptionHandler{ context , thro ->

        Log.i("main",  ""+thro.message )
    }

    fun getCountries():LiveData<AirLineModel>{
        val mutableLiveData = MutableLiveData<AirLineModel>()

        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler ).launch {
           val response =  repo.getCountries()
            response.first?.let {
                mutableLiveData.postValue(it)
            }
            handleError(response.second)
            Log.i("main", "handleError: "+response.second)
        }
        return mutableLiveData
    }

    fun getAirLines():LiveData<List<AirLineModel>>{
        val mutableLiveData = MutableLiveData<List<AirLineModel>>()

        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler ).launch {
            val response =  repo.getAirLines()
            handleError(response.code())
            if(response.isSuccessful){
                mutableLiveData.postValue(response.body())
            }
        }
        return mutableLiveData
    }



    fun handleError(code : Int){
        when(code){
          400 ->{ Log.i("main", "handleError: ")}
        }
    }


}