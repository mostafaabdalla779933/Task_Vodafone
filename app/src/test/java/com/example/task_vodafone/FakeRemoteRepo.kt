package com.example.task_vodafone


import com.example.entity.AirLineEntity
import com.example.network.model.AirLineModel


object DummyData{

   val listOfAirlinesModel = listOf(AirLineModel("1","",""),AirLineModel("2","",""),AirLineModel("3","",""))

   val listOfAirlinesEntities = listOf(AirLineEntity("1","",""),AirLineEntity("2","",""),AirLineEntity("3","",""))
}

const val SUCCESS_CODE = 200