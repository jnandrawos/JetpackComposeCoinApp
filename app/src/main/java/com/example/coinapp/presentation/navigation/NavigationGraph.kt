package com.example.coinapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coinapp.presentation.screens.CoinsScreen
import com.example.coinapp.presentation.screens.LogsScreen
import com.example.coinapp.presentation.screens.WalletScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Coins.route
    ) {
        composable(route = BottomBarScreen.Coins.route) {
            CoinsScreen(modifier)
        }
        composable(route = BottomBarScreen.Wallet.route) {
            WalletScreen(modifier)
        }
        composable(route = BottomBarScreen.Logs.route) {
            LogsScreen(modifier)
        }
    }
}