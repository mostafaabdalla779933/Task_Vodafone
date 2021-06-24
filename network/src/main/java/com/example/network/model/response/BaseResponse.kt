package com.evaph.network.model.response

import com.google.gson.annotations.SerializedName

class BaseResponse<T> {
    @SerializedName("data")
    var data: T? = null
}