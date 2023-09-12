package com.example.coinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coinapp.presentation.components.CustomBottomAppBar
import com.example.coinapp.presentation.navigation.BottomBarScreen
import com.example.coinapp.presentation.navigation.NavigationGraph
import com.example.coinapp.ui.theme.CoinAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinAppTheme {
                MainScreen()
            }
        }
    }
}

@Preview
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val screens = listOf(
        BottomBarScreen.Coins,
        BottomBarScreen.Wallet,
        BottomBarScreen.Logs
    )
    Scaffold(
        bottomBar = { CustomBottomAppBar(navController = navController, screens) },
        topBar = {
            if (screens.any { it.route == navController.currentBackStackEntryAsState().value?.destination?.route })
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                ) {
                    Text(
                        text = screens.firstOrNull { it.route == navController.currentBackStackEntryAsState().value?.destination?.route }?.label
                            ?: "",
                        color = Color.White,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
                    )
                }
        }
    )
    { paddingValues ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .background(Color.Black)
        )
    }
}