package com.example.coinapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Coins : BottomBarScreen(
        route = "home",
        label = "Home",
        icon = Icons.Rounded.Home
    )

    object Wallet : BottomBarScreen(
        route = "wallet",
        label = "Wallet",
        icon = Icons.Rounded.Wallet
    )

    object Logs : BottomBarScreen(
        route = "logs",
        label = "Logs",
        icon = Icons.Rounded.Stop
    )
}