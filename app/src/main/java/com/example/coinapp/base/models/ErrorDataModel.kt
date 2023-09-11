package com.example.coinapp.base.models

import com.google.gson.annotations.SerializedName

data class ErrorDataModel(
    @SerializedName("message") val message: String?,
    @SerializedName("error_code") val errorCode: String?,
    @SerializedName("text_error") val textError: String?
)