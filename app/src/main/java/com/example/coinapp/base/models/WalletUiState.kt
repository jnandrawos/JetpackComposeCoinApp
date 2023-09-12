package com.example.coinapp.base.models

import com.example.coinapp.presentation.components.LoaderStatus

data class WalletUiState(
    val valueBTC: String? = null,
    val valueUSD: Double? = null,
    val status: LoaderStatus? = null
)
