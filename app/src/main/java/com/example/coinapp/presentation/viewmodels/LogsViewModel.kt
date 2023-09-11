package com.example.coinapp.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapp.base.models.LogsUiState
import com.example.coinapp.source.local.TransactionModel
import com.example.coinapp.source.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogsViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {
    private val _logsState = mutableStateOf(LogsUiState())
    val logsState: State<LogsUiState> = _logsState

    init {
        getTransaction()
    }

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


}