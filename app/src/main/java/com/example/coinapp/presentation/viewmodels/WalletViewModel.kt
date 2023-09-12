package com.example.coinapp.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapp.base.models.LogsUiState
import com.example.coinapp.base.models.Status
import com.example.coinapp.base.models.WalletUiState
import com.example.coinapp.source.local.TransactionModel
import com.example.coinapp.source.remote.service.WalletService
import com.example.coinapp.source.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val walletService: WalletService
) : ViewModel() {
    private val _logsState = mutableStateOf(LogsUiState())
    val logsState: State<LogsUiState> = _logsState

    private val _walletState = mutableStateOf(WalletUiState())
    val walletState: State<WalletUiState> = _walletState

    fun insertTransaction(transactionModel: TransactionModel) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepository.insert(transactionModel)
            getTransaction()
        }
    }

    fun getTransaction() {
        viewModelScope.launch {
            transactionRepository.getAllTransactions().collectLatest { transactions ->
                _logsState.value = logsState.value.copy(
                    logsList = transactions
                )
            }
        }
    }

    fun getWallet() {
        viewModelScope.launch {
            walletService.getWallet().collect { result ->
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
            walletService.depositWallet().collect { result ->
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
            walletService.withdrawWallet().collect { result ->
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