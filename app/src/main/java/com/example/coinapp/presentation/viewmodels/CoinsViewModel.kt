package com.example.coinapp.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapp.base.models.CoinsUiState
import com.example.coinapp.base.models.Status
import com.example.coinapp.base.models.WalletUiState
import com.example.coinapp.source.remote.service.CoinsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val coinsService: CoinsService
) : ViewModel() {
    private val _coinsState = mutableStateOf(CoinsUiState())
    val coinsState: State<CoinsUiState> = _coinsState
    private val _walletState = mutableStateOf(WalletUiState())
    val walletState: State<WalletUiState> = _walletState

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        getCoins()
        getWallet()
    }

    fun getCoins() {
        viewModelScope.launch {
            coinsService.getCoins().collect { result ->
                when (result.status) {
                    Status.LOADING -> {
                        _coinsState.value = coinsState.value.copy(
                            coinsList = result.data?.data.orEmpty(),
                            isLoading = true
                        )
                    }

                    Status.SUCCESS -> {
                        _coinsState.value = coinsState.value.copy(
                            coinsList = result.data?.data.orEmpty(),
                            isLoading = false
                        )
                    }

                    Status.ERROR -> {
                        _coinsState.value = coinsState.value.copy(
                            coinsList = result.data?.data.orEmpty(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun getWallet() {
        viewModelScope.launch {
            coinsService.getWallet().collect { result ->
                when (result.status) {
                    Status.LOADING -> {
                        _walletState.value = walletState.value.copy(
                            valueBTC = result.data?.data?.valueBTC,
                            valueUSD = result.data?.data?.valueUSD,
                            isLoading = true
                        )
                    }

                    Status.SUCCESS -> {
                        _walletState.value = walletState.value.copy(
                            valueBTC = result.data?.data?.valueBTC,
                            valueUSD = result.data?.data?.valueUSD,
                            isLoading = false
                        )
                    }

                    Status.ERROR -> {
                        _walletState.value = walletState.value.copy(
                            valueBTC = result.data?.data?.valueBTC,
                            valueUSD = result.data?.data?.valueUSD,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun depositWallet() {
        viewModelScope.launch {
            coinsService.depositWallet().collect { result ->
                when (result.status) {
                    Status.LOADING -> {
                        _walletState.value = walletState.value.copy(
                            valueBTC = result.data?.data?.valueBTC,
                            valueUSD = result.data?.data?.valueUSD,
                            isLoading = true
                        )
                    }

                    Status.SUCCESS -> {
                        _walletState.value = walletState.value.copy(
                            valueBTC = result.data?.data?.valueBTC,
                            valueUSD = result.data?.data?.valueUSD,
                            isLoading = false
                        )
                    }

                    Status.ERROR -> {
                        _walletState.value = walletState.value.copy(
                            valueBTC = result.data?.data?.valueBTC,
                            valueUSD = result.data?.data?.valueUSD,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun withdrawWallet() {
        viewModelScope.launch {
            coinsService.withdrawWallet().collect { result ->
                when (result.status) {
                    Status.LOADING -> {
                        _walletState.value = walletState.value.copy(
                            valueBTC = result.data?.data?.valueBTC,
                            valueUSD = result.data?.data?.valueUSD,
                            isLoading = true
                        )
                    }

                    Status.SUCCESS -> {
                        _walletState.value = walletState.value.copy(
                            valueBTC = result.data?.data?.valueBTC,
                            valueUSD = result.data?.data?.valueUSD,
                            isLoading = false
                        )
                    }

                    Status.ERROR -> {
                        _walletState.value = walletState.value.copy(
                            valueBTC = result.data?.data?.valueBTC,
                            valueUSD = result.data?.data?.valueUSD,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}