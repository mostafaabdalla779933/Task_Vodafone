package com.example.task_vodafone.ui.airlines


import androidx.lifecycle.ViewModel
import com.example.entity.AirLineEntity
import com.example.task_vodafone.repo.ILocalRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AirLineViewModel  @Inject constructor(val repo: ILocalRepo): ViewModel(){

    var airlineList  = MutableStateFlow<List<AirLineEntity>>(listOf())
    var airlineListFilter = listOf<AirLineEntity>()

    // get the list of airlines from caching
    fun getAirLines(){
            CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
                repo.getAirlines().let { list ->
                    airlineListFilter=list
                    airlineList.emit(list)
                }
            }
    }

    // filter the list according to the query String
    fun filter(query : String ){
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler ).launch {
            airlineList
                .emit(airlineListFilter.filter { e -> e.name?.startsWith(query.trim(), true) ?: false }.map { e->e.also { e.textHighlight = query } })

        }
    }

    // add item to the existing list
    fun addItem(item:AirLineEntity){
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler ).launch {
            repo.addAirline(item)
        }
    }

    private val coroutineExceptionHandler= CoroutineExceptionHandler{ context, thro ->

    }

}