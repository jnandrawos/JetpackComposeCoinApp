package com.example.coinapp.source.remote.service

import com.example.coinapp.base.di.ApplicationModule
import com.example.coinapp.base.models.APIResponse
import com.example.coinapp.base.models.CoinData
import com.example.coinapp.base.models.CommonResponse
import com.example.coinapp.base.models.Status
import com.example.coinapp.base.models.WalletData
import com.example.coinapp.source.remote.api.CoinsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CoinsService @Inject constructor(
    private val coinsApi: CoinsApi
) {
    private suspend fun getCoinsRemote(): APIResponse<CommonResponse<List<CoinData>>> =
        ApplicationModule().getResponse {
            coinsApi.getCoins()
        }

    suspend fun
            getCoins(): Flow<APIResponse<CommonResponse<List<CoinData>>>> {
        return flow {
            emit(APIResponse.loading())
            val response =
                getCoinsRemote()
            emit(response)
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(
                    APIResponse(
                        Status.ERROR,
                        data = null,
                        Exception(it),
                        it.message.toString()
                    )
                )
            }
    }

    private suspend fun getWalletRemote(): APIResponse<CommonResponse<WalletData>> =
        ApplicationModule().getResponse {
            coinsApi.getWallet()
        }

    suspend fun
            getWallet(): Flow<APIResponse<CommonResponse<WalletData>>> {
        return flow {
            emit(APIResponse.loading())
            val response =
                getWalletRemote()
            emit(response)
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(
                    APIResponse(
                        Status.ERROR,
                        data = null,
                        Exception(it),
                        it.message.toString()
                    )
                )
            }
    }


    private suspend fun depositWalletRemote(): APIResponse<CommonResponse<WalletData>> =
        ApplicationModule().getResponse {
            coinsApi.depositWallet()
        }

    suspend fun
            depositWallet(): Flow<APIResponse<CommonResponse<WalletData>>> {
        return flow {
            emit(APIResponse.loading())
            val response =
                depositWalletRemote()
            emit(response)
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(
                    APIResponse(
                        Status.ERROR,
                        data = null,
                        Exception(it),
                        it.message.toString()
                    )
                )
            }
    }


    private suspend fun withdrawWalletRemote(): APIResponse<CommonResponse<WalletData>> =
        ApplicationModule().getResponse {
            coinsApi.withdrawWallet()
        }

    suspend fun
            withdrawWallet(): Flow<APIResponse<CommonResponse<WalletData>>> {
        return flow {
            emit(APIResponse.loading())
            val response =
                withdrawWalletRemote()
            emit(response)
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(
                    APIResponse(
                        Status.ERROR,
                        data = null,
                        Exception(it),
                        it.message.toString()
                    )
                )
            }
    }

}