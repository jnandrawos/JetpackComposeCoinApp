package com.example.coinapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.coinapp.presentation.components.TransactionTile
import com.example.coinapp.presentation.viewmodels.LogsViewModel

@Composable
fun LogsScreen(
    modifier: Modifier,
    logsViewModel: LogsViewModel = hiltViewModel()
) {
    val state by remember {
        logsViewModel.logsState
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "BlockChain",
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        state.logsList.forEachIndexed { index, transaction ->
            TransactionTile(
                date = transaction.transactionTime.toString(),
                isDeposit = transaction.isDeposit == true
            )
            if (index < state.logsList.size) Spacer(modifier = Modifier.height(20.dp))

        }
    }
}