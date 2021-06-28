package com.example.task_vodafone.ui.airlines

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.entity.AirLineEntity
import com.example.task_vodafone.repo.ILocalRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class AirLineViewModel  @Inject constructor(val repo: ILocalRepo): ViewModel(){

    var airlineList  : MediatorLiveData<List<AirLineEntity>> = MediatorLiveData()
    var airlineListFilter = listOf<AirLineEntity>()

    // get the list of airlines from caching
    fun getAirLines(){
            CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
               airlineList.addSource(repo.getAirlines()){
                   airlineListFilter=it
                   airlineList.postValue(it)
               }
            }
    }

    // filter the list according to the query String
    fun filter(query : String ){
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler ).launch {
            airlineList
                .postValue(airlineListFilter.filter { e -> e.name?.startsWith(query, true) ?: false
                        || e.country?.startsWith(query, true) ?: false
                        || e.id.startsWith(query,true) })

        }
    }

    // add item to the existing list
    fun addItem(item:AirLineEntity){
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler ).launch {
            repo.addAirline(item)
        }
    }

    val coroutineExceptionHandler= CoroutineExceptionHandler{ context , thro ->

        Log.i("main", "excpetion")
    }

}