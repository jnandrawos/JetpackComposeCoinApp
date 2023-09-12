package com.example.coinapp.base.models

import com.example.coinapp.presentation.components.LoaderStatus

data class CoinsUiState(
    val coinsList: List<CoinData>? = emptyList(),
    val status: LoaderStatus? = null
)
