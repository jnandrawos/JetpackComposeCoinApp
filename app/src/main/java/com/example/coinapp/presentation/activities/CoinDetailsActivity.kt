package com.example.coinapp.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.coinapp.base.utils.Converters
import com.example.coinapp.presentation.screens.CoinsDetailsScreen
import com.example.coinapp.ui.theme.CoinAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoinDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val coinData = Converters.getCoin(intent.extras?.getString("coin_bundle") ?: "")
        setContent {
            CoinAppTheme {
                CoinsDetailsScreen(
                    modifier = Modifier.background(Color.Black),
                    image = coinData.icon,
                    name = coinData.name,
                    symbol = coinData.symbol
                )
            }
        }
    }
}