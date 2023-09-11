package com.example.coinapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coinapp.presentation.viewmodels.WalletViewModel
import com.example.coinapp.source.local.TransactionModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview
@Composable
fun WalletScreen(
    modifier: Modifier = Modifier,
    walletViewModel: WalletViewModel = hiltViewModel(),
) {
    val state by remember {
        walletViewModel.walletState
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.Blue, shape = RoundedCornerShape(10.dp))
                    .padding(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Balance",
                        color = Color.White,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "${state.valueUSD ?: "..."} $",
                            color = Color.White,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "${state.valueBTC ?: "..."} BTC",
                            color = Color.White,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                if (state.isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier
                        .background(
                            Color.Green.copy(alpha = if (state.isLoading) 0.5f else 1f),
                            shape = RoundedCornerShape(30.dp)
                        )
                        .weight(1f)
                        .padding(vertical = 10.dp)
                        .clickable {
                            if (state.isLoading.not()) {
                                walletViewModel.depositWallet()
                                walletViewModel.insertTransaction(
                                    TransactionModel(
                                        transactionTime = SimpleDateFormat(
                                            "dd-MM-yyyy HH:mm", Locale.getDefault()
                                        ).format(Date()), isDeposit = true
                                    )
                                )
                            }
                        },
                    text = "Deposit",
                    color = Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    modifier = Modifier
                        .background(
                            Color.Red.copy(alpha = if (state.isLoading) 0.5f else 1f),
                            shape = RoundedCornerShape(30.dp)
                        )
                        .weight(1f)
                        .padding(10.dp)
                        .clickable {
                            if (state.isLoading.not()) {
                                walletViewModel.withdrawWallet()
                                walletViewModel.insertTransaction(
                                    TransactionModel(
                                        transactionTime = SimpleDateFormat(
                                            "dd-MM-yyyy HH:mm", Locale.getDefault()
                                        ).format(Date()), isDeposit = false
                                    )
                                )
                            }
                        },
                    text = "Withdraw",
                    color = Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }

    }
}