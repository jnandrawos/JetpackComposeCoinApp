package com.example.coinapp.base.models

import com.google.gson.annotations.SerializedName

data class CoinData(
    @SerializedName("symbol") var symbol: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("icon") var icon: String? = null
)
