package com.example.coinapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coinapp.presentation.screens.CoinsDetailsScreen
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
            CoinsScreen(modifier, navController)
        }
        composable(route = BottomBarScreen.Wallet.route) {
            WalletScreen(modifier)
        }
        composable(route = BottomBarScreen.Logs.route) {
            LogsScreen(modifier)
        }
        composable(
            route = "${BottomBarScreen.Details.route}/{image}/{name}/{symbol}", arguments = listOf(
                navArgument("image") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType },
                navArgument("symbol") { type = NavType.StringType },
            )
        ) {
            CoinsDetailsScreen(
                modifier,
                it.arguments?.getString("image"),
                it.arguments?.getString("name"),
                it.arguments?.getString("symbol"),
            )
        }
    }
}