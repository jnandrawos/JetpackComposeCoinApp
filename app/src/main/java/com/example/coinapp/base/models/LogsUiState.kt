package com.example.coinapp.base.models

import com.example.coinapp.source.local.TransactionModel

data class LogsUiState(
    val logsList: List<TransactionModel> = emptyList(),
)
