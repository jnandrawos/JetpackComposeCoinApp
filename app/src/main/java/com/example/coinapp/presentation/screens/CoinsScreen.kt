package com.example.coinapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.coinapp.MyApp
import com.example.coinapp.base.extensions.encodeUrl
import com.example.coinapp.base.extensions.obtainViewModel
import com.example.coinapp.base.models.CoinData
import com.example.coinapp.presentation.components.CustomCoinTile
import com.example.coinapp.presentation.components.LazyLoader
import com.example.coinapp.presentation.navigation.BottomBarScreen
import com.example.coinapp.presentation.viewmodels.CoinsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CoinsScreen(
    modifier: Modifier,
    navController: NavHostController,
) {
    val coinsViewModel: CoinsViewModel by lazy {
        obtainViewModel(
            MyApp.instance.activity,
            CoinsViewModel::class.java,
            MyApp.instance.activity.defaultViewModelProviderFactory
        )
    }
    val state by remember {
        coinsViewModel.coinsState
    }


    val isRefreshing = coinsViewModel.isRefreshing.collectAsState().value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { coinsViewModel.getCoins() },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyLoader(
                status = state.status
            ) {
                CoinsSelector(state.coinsList.orEmpty(), navController)
            }
        }
    }
}

@Composable
fun CoinsSelector(data: List<CoinData>, navController: NavHostController) {
    LazyColumn(
        Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        itemsIndexed(data) { index, item ->
            CustomCoinTile(
                title = item.name.toString(), subtitle = item.symbol, imageUrl = item.icon.orEmpty()
            ) { image, name, symbol ->
                navController.navigate("${BottomBarScreen.Details.route}/${image?.encodeUrl()}/$name/$symbol")
            }
            if (index < data.size) Spacer(modifier = Modifier.height(20.dp))
        }
    }
}