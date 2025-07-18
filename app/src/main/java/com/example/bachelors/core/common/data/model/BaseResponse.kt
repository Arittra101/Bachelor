package com.example.bachelors.core.common.data.model

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("status") var status: String? = null
    @SerializedName("message") var message: String? = null
    @SerializedName(value = "status_code", alternate = ["statusCode"]) open var statusCode: Int? = null
    @SerializedName("error") var error: String? = null
}
