package com.example.coinapp.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapp.base.models.CoinsUiState
import com.example.coinapp.base.models.Status
import com.example.coinapp.presentation.components.LoaderStatus
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
    var coinsState = mutableStateOf(CoinsUiState())

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()


    fun getCoins() {
        viewModelScope.launch {
            coinsService.getCoins().collect { result ->
                when (result.status) {
                    Status.LOADING -> {
                        coinsState.value =
                            CoinsUiState(
                                coinsList = result.data?.data,
                                status = LoaderStatus.LOADING
                            )

                    }

                    Status.SUCCESS -> {
                        coinsState.value =
                            CoinsUiState(
                                coinsList = result.data?.data,
                                status = LoaderStatus.SUCCESS
                            )
                    }

                    Status.ERROR -> {
                        coinsState.value =
                            CoinsUiState(
                                coinsList = result.data?.data, status = LoaderStatus.ERROR
                            )
                    }
                }
            }
        }
    }
}