package com.example.coinapp.source.remote.api

import com.example.coinapp.base.models.CoinData
import com.example.coinapp.base.models.CommonResponse
import com.example.coinapp.base.models.WalletData
import retrofit2.Response
import retrofit2.http.GET

interface CoinsApi {

    @GET("getCoins.php")
    suspend fun getCoins(): Response<CommonResponse<List<CoinData>>>

}