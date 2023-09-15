package com.example.coinapp.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.coinapp.presentation.navigation.BottomBarScreen

@Composable
fun CustomBottomAppBar(navController: NavHostController, screens: List<BottomBarScreen>) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination
    NavigationBar(
        containerColor = Color.White
    ) {
        screens.forEach { item ->
            NavigationBarItem(
                selected = currentRoute?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(BottomBarScreen.Coins.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(text = item.label) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "",
                    )
                },
                colors = getNavigationBarItemColors()
            )
        }
    }
}

@Composable
fun getNavigationBarItemColors() =
    NavigationBarItemColors(
        selectedIconColor = Color.Blue,
        selectedTextColor = Color.Blue,
        selectedIndicatorColor = Color.White,
        unselectedIconColor = Color.DarkGray,
        unselectedTextColor = Color.DarkGray,
        disabledIconColor = Color.Red,
        disabledTextColor = Color.Red,
    )