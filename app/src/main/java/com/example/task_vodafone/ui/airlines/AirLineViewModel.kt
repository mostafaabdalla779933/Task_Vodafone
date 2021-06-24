package com.example.task_vodafone.ui.airlines

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.entity.AirLineEntity
import com.example.task_vodafone.repo.LocalRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class AirLineViewModel  @Inject constructor(val repo: LocalRepo): ViewModel(){

    var airlineList = MutableLiveData<MutableList<AirLineEntity>>()
    var airlineListFilter = MutableLiveData<List<AirLineEntity>>()

    fun getAirLines(){
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler ).launch {
            val response =  repo.getAirlines()
            airlineList.postValue(response.toMutableList())
        }
    }

    fun filter(query : String ){

        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler ).launch {
            airlineListFilter
                .postValue(airlineList.value?.filter { e -> e.name?.startsWith(query, true) ?: false
                        || e.country?.startsWith(query, true) ?: false
                        || e.id.startsWith(query,true) })

        }
    }


    fun addItem(item:AirLineEntity){

       val list = airlineList.value
        list?.let {
            it.add(item)
            airlineList.postValue(it)
        }
    }

    val coroutineExceptionHandler= CoroutineExceptionHandler{ context , thro ->
    }


}