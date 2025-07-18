package com.example.bachelors.core.common.data.model

import com.google.gson.annotations.SerializedName

data class HistoryItemResponse(
    @SerializedName("data") var data: List<HistoryItem>? = null
): BaseResponse()

