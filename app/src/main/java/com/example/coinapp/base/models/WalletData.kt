package com.example.coinapp.base.models

import com.google.gson.annotations.SerializedName

data class WalletData(
    @SerializedName("valueBTC") var valueBTC: String? = null,
    @SerializedName("valueUSD") var valueUSD: Double? = null
)
