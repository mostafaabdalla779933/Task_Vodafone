package com.example.task_vodafone

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.AirLineModel
import com.example.network.model.response.AirLineResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class CountryViewModel  @Inject constructor(val repo: CountryRepo): ViewModel(){



//    fun getCities(): LiveData<List<CityEntity>> {
//        val mutableLiveData = MutableLiveData<List<CityEntity>>()
//        viewModelScope.launchCatching(
//            block = {
//                val cities = repo.getCities()
//                mutableLiveData.postValue(cities)
//            }, onError = ::handleError
//        )
//        return mutableLiveData
//    }

    val coroutineExceptionHandler= CoroutineExceptionHandler{ context , thro ->

        Log.i("main",  ""+thro.message )
    }





    fun getCountries():LiveData<AirLineModel>{
        val mutableLiveData = MutableLiveData<AirLineModel>()
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler ).launch {
           val response =  repo.getCountries()
            mutableLiveData.postValue(response!!)
        }
        return mutableLiveData
    }


}