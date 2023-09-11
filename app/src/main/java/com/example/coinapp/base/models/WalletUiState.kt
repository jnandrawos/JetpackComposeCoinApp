package com.example.coinapp.base.models

data class WalletUiState(
    val valueBTC: String? = null,
    val valueUSD: Double? = null,
    val isLoading: Boolean = false
)
