package com.example.coinapp.source.remote.api

import com.example.coinapp.base.models.CommonResponse
import com.example.coinapp.base.models.WalletData
import retrofit2.Response
import retrofit2.http.GET

interface WalletApi {
    @GET("getWallet.php")
    suspend fun getWallet(): Response<CommonResponse<WalletData>>

    @GET("depositWallet.php")
    suspend fun depositWallet(): Response<CommonResponse<WalletData>>

    @GET("withdrawWallet.php")
    suspend fun withdrawWallet(): Response<CommonResponse<WalletData>>
}