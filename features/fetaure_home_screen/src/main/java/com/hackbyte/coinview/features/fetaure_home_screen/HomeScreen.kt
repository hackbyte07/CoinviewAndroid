package com.hackbyte.coinview.features.fetaure_home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackbyte.coinview.features.common.components.BottomTransition
import com.hackbyte.coinview.features.common.components.NetworkIndicator
import com.hackbyte.coinview.features.feature_detail_screen.destinations.DetailScreenDestination
import com.hackbyte.coinview.features.fetaure_home_screen.components.MarketDataCard
import com.hackbyte.coinview.features.fetaure_home_screen.components.ProfileDialog
import com.hackbyte.coinview.features.fetaure_home_screen.components.TrendingCard
import com.hackbyte.coinview.features.fetaure_home_screen.destinations.HomeScreenDestination
import com.hackbyte.coinview.features.fetaure_home_screen.utils.OnBottomReached
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import timber.log.Timber


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination(style = BottomTransition::class)
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    navigator: DestinationsNavigator
) {
    val lazyStateOfColumn = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val snackBarHostState = SnackbarHostState()
    val trendingCoinsData = viewModel.trendingCoinsState.data
    val listStateOfMarket = rememberLazyListState()
    val (showDialog, setShowDialog) = remember {
        mutableStateOf(false)
    }
    val marketDataState = viewModel.marketDataState
    val networkState = viewModel.networkState
    val screenHeight = LocalConfiguration.current.screenHeightDp
    Timber.d("creating home Screen")
    Scaffold(
        topBar = {
            when (networkState.hasNetwork) {
                true -> {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = "Coinview",
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                                fontStyle = MaterialTheme.typography.titleLarge.fontStyle
                            )
                        },
                        actions = {
                            IconButton(onClick = { setShowDialog(true) }) {
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = "account"
                                )
                            }
                        }
                    )
                }
                false -> {
                    NetworkIndicator()
                }
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Text(
                text = "Trending Coins",
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle
            )
            Spacer(modifier = Modifier.padding(top = 15.dp))
            LazyRow(state = lazyStateOfColumn) {
                items(trendingCoinsData.coins) { items ->
                    val coin = items.item
                    Spacer(modifier = Modifier.padding(start = 15.dp))
                    TrendingCard(
                        imageUrl = coin.thumb,
                        showPlaceHolder = false,
                        symbol = coin.symbol,
                        slug = coin.slug,
                        price = coin.price_btc,
                        marketCapRank = coin.market_cap_rank.toString()
                    ) {
                        val id = coin.id
                        if (id.isEmpty() || id.isBlank()) {
                            scope.launch { snackBarHostState.showSnackbar("No more details available") }
                            return@TrendingCard
                        }
                        navigator.navigate(DetailScreenDestination(id)) {
                            launchSingleTop = true
                            popUpTo(HomeScreenDestination)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                text = "Current Market ",
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                modifier = Modifier.padding(start = 25.dp)
            )
            Spacer(modifier = Modifier.padding(top = 15.dp))
            LazyColumn(
                Modifier.height((screenHeight).dp),
                state = listStateOfMarket
            ) {

                items(viewModel.marketDataState.data.coinsMarketItem) { item ->
                    MarketDataCard(
                        currentPrice = item.current_price,
                        image = item.image,
                        marketCapRank = item.market_cap_rank,
                        name = item.name,
                        priceChange24hrsPercentage = item.price_change_percentage_24h,
                        symbol = item.symbol,
                        isLoading = marketDataState.loading
                    ) {
                        val id = item.id
                        if (id.isEmpty() || id.isBlank()) {
                            scope.launch { snackBarHostState.showSnackbar("No more data available") }
                            return@MarketDataCard
                        }
                        navigator.navigate(DetailScreenDestination(id)) {
                            launchSingleTop = true
                            popUpTo(HomeScreenDestination)
                        }
                    }
                    Spacer(modifier = Modifier.padding(bottom = 7.5.dp))
                }
            }

        }
        listStateOfMarket.OnBottomReached(4) {
            viewModel.getMarkets()
        }

        if (showDialog) {
            val profileUsername = viewModel.userName
            Timber.tag("username").d(profileUsername)
            ProfileDialog(username = profileUsername, setShowDialog) {
                viewModel.logout()
            }
        }
        SnackbarHost(hostState = snackBarHostState)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {

}