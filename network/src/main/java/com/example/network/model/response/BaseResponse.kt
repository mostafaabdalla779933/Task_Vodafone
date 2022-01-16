package com.example.network.model.response

import com.google.gson.annotations.SerializedName

class BaseResponse<T> {

    var data: T? = null
    var message: String? = null
}

