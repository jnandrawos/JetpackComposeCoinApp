package com.example.coinapp.base.models

import com.google.gson.annotations.SerializedName

data class CommonResponse<T>(
    @SerializedName("result") var result: String? = null,
    @SerializedName("data") var data: T? = null
)
