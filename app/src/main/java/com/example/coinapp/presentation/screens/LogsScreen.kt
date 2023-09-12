package com.example.coinapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coinapp.presentation.components.TransactionTile
import com.example.coinapp.presentation.viewmodels.WalletViewModel

@Composable
fun LogsScreen(
    modifier: Modifier,
    walletViewModel: WalletViewModel = hiltViewModel()
) {
    val state by remember {
        walletViewModel.logsState
    }
    SideEffect {
        walletViewModel.getTransaction()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        state.logsList.forEachIndexed { index, transaction ->
            TransactionTile(
                date = transaction.transactionTime.toString(),
                isDeposit = transaction.isDeposit == true
            )
            if (index < state.logsList.size) Spacer(modifier = Modifier.height(20.dp))

        }
    }
}