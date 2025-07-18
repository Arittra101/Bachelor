package com.example.bachelors.core.common.data.model

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("data") var data: List<UserInfo>? = null
) : BaseResponse()
