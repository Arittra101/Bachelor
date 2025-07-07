package com.example.bachelors.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data") var data: ArrayList<BaseResponse>? = null
)