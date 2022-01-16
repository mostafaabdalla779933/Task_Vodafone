package com.example.task_vodafone.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.entity.AirLineEntity
import com.example.network.model.AirLineModel
import com.example.task_vodafone.repo.ILocalRepo
import com.example.task_vodafone.repo.IRemoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(private val remoteRepo: IRemoteRepo, private val localRepo : ILocalRepo): ViewModel() {

    var stateLiveData =  MutableLiveData<String?>()
    var errorLineLiveData = MutableLiveData<Boolean>()
    var dataCached = MutableLiveData<Boolean>()
    private var job : Job? = null

    // call api and call function to cache the response
    fun getAirLines(){
        job =  CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
                if(!localRepo.getCached()) {
                    val response = remoteRepo.getAirLines()
                    handleError(response.code())
                    if (response.isSuccessful) {
                        cacheAirlines(response.body())
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

    // cache the response  in the room
    private suspend fun cacheAirlines(airlines : List<AirLineModel>?){
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
                stateLiveData.postValue("connection failed")
            }
            code >= 500 -> {
                errorLineLiveData.postValue(true)
                stateLiveData.postValue("server problem")
            }
        }
    }

    private val coroutineExceptionHandler= CoroutineExceptionHandler{ _, _ ->
        errorLineLiveData.postValue(true)
    }


    fun clear(){
        if(job!=null && !job?.isActive!!)
           job?.cancel()
    }

}

