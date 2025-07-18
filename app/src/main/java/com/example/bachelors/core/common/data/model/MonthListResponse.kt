package com.example.bachelors.core.common.data.model

import com.google.gson.annotations.SerializedName

data class MonthListResponse(
    @SerializedName("data") val data: List<List<String>>
) : BaseResponse()
