package com.hackbyte.coinview.features.feature_detail_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.hackbyte.coinview.features.common.components.CircularLoading
import com.hackbyte.coinview.features.common.components.ScreenTransition
import com.hackbyte.coinview.features.feature_detail_screen.components.TickerComponent
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination(style = ScreenTransition::class)
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel = getViewModel() as DetailViewModel
) {
    val scrollState = rememberScrollState()
    val swipeRefreshState =
        rememberSwipeRefreshState(isRefreshing = viewModel.coinsDetailState.isRefreshing)
    LaunchedEffect(key1 = true) {
        viewModel.getCoinsDetail(id)
    }
    SwipeRefresh(state = swipeRefreshState, modifier = Modifier.fillMaxSize(), onRefresh = {
        viewModel.getCoinsDetail(id)
    }, refreshTriggerDistance = 150.dp) {
        val coinsDetail = viewModel.coinsDetailState.coinDetails
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(coinsDetail.image.large)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .size(200.dp)
                    .padding(25.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                contentDescription = "coin image"
            )
            if (coinsDetail.categories.isNotEmpty()) {
                Text(
                    text = "Categories",
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                )
                Spacer(modifier = Modifier.padding(top = 5.dp))
                FlowRow(
                    Modifier.padding(bottom = 15.dp),
                    crossAxisAlignment = FlowCrossAxisAlignment.Center
                ) {
                    coinsDetail.categories.forEach {
                        Spacer(modifier = Modifier.padding(start = 15.dp))
                        AssistChip(onClick = { /*TODO*/ }, label = { Text(text = it) })
                    }
                }
            }
            Text(
                text = coinsDetail.name,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
            )
            Spacer(modifier = Modifier.padding(top = 15.dp))
            Text(
                text = "Symbol ${coinsDetail.symbol}",
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.padding(top = 15.dp))
            FlowRow(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
            ) {
                Text(
                    text = "MarketCap Rank ${coinsDetail.market_cap_rank}",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                    fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                    fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.padding(start = 15.dp))
                Text(
                    text = "CoinGecko Rank ${coinsDetail.coingecko_rank}",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                    fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                    fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.padding(start = 15.dp))
                Text(
                    text = "CoinGecko Score ${coinsDetail.coingecko_score}",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                    fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                    fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.padding(start = 15.dp))
                Text(
                    text = "Community Score ${coinsDetail.community_score}",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                    fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                    fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.padding(start = 15.dp))
                Text(
                    text = "Liquidity Score ${coinsDetail.liquidity_score}",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                    fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                    fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.padding(start = 15.dp))
                Text(
                    text = "Blocktime in minutes ${coinsDetail.block_time_in_minutes}",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                    fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                    fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Spacer(modifier = Modifier.padding(top = 25.dp))
            Text(
                text = "Description",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
            )
            Spacer(modifier = Modifier.padding(top = 15.dp))
            if (coinsDetail.description.en.isNotEmpty()) {
                LazyColumn(modifier = Modifier.height((LocalConfiguration.current.screenHeightDp / 2).dp)) {
                    items(coinsDetail.description.en) {
                        Text(
                            text = it,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                            modifier = Modifier.padding(start = 15.dp, end = 15.dp),

                            )
                    }
                }
            }
            if (viewModel.coinsDetailState.coinDetails.tickers.isNotEmpty()) {
                Spacer(modifier = Modifier.padding(top = 25.dp))
                LazyColumn(modifier = Modifier.height(LocalConfiguration.current.screenHeightDp.dp)) {
                    items(viewModel.coinsDetailState.coinDetails.tickers) {
                        TickerComponent(ticker = it)
                        Spacer(modifier = Modifier.padding(bottom = 15.dp))
                    }
                }
            }

        }

        CircularLoading(viewModel.coinsDetailState.loading, MaterialTheme.colorScheme.background)
    }

}


@Preview
@Composable
fun PreviewDetailScreen() {
    DetailScreen(id = "bitcoin")
}