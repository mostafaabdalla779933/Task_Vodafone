package com.example.network.model.response

import com.example.network.model.AirLineModel
import com.google.gson.annotations.SerializedName

data class AirLineResponse(

	@field:SerializedName("Response")
	val response: List<AirLineModel>
)

