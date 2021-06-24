package com.evaph.network.model.response
import com.google.gson.annotations.SerializedName

//class ErrorResponse(
//    @SerializedName("message") override val message: String? = null,
//    @SerializedName("errorList") val errorList: ArrayList<ErrorModel> = ArrayList(),
//    var name: String? = null,
//    var code: Int = 0,
//) : Exception() {
//
//    override fun toString(): String {
//        var error = ""
//        if (code != 0)
//            error += "$code "
//        if (message != null && message.isNotEmpty())
//            error += message
//        else if (name != null)
//            error += name
//        if (errorList.isNotEmpty())
//            errorList.forEach { error += "\n${it.message}" }
//        return error.trim()
//    }
//}