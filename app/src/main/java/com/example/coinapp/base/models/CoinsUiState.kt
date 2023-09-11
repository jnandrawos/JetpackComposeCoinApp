package com.example.coinapp.base.models

data class CoinsUiState(
    val coinsList: List<CoinData> = emptyList(),
    val isLoading: Boolean = false
)
