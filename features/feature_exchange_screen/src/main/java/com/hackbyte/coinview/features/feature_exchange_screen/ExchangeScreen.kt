package com.hackbyte.coinview.features.feature_exchange_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hackbyte.coinview.features.common.components.BottomTransition
import com.hackbyte.coinview.features.common.components.CircularLoading
import com.hackbyte.coinview.features.common.components.ErrorScreen
import com.hackbyte.coinview.features.feature_exchange_screen.components.ExchangeCard
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.getViewModel


@Composable
@Destination(style = BottomTransition::class)
fun ExchangeScreen(

) {
    val viewModel = getViewModel() as ExchangeViewModel
    val exchangeDataState = viewModel.exchangesState
    when (viewModel.networkState.hasNetwork) {
        true -> {
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(top = 15.dp))
                Text(
                    text = "Top 50 Exchanges",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.padding(top = 15.dp))
                LazyColumn {
                    items(exchangeDataState.data) {
                        ExchangeCard(
                            imageUrl = it.image,
                            name = it.name,
                            trustScoreRank = it.trust_score.toString(),
                            description = it.description,
                            tradeVolume24hrs = it.trade_volume_24h_btc
                        )
                        Spacer(modifier = Modifier.padding(bottom = 15.dp))
                    }
                }
            }
            if (exchangeDataState.data.isEmpty()) {
                ErrorScreen(message = "Network slow")
            }
        }
        false -> {
            ErrorScreen(message = "Network Error")
        }
    }
    CircularLoading(exchangeDataState.isLoading, MaterialTheme.colorScheme.background)
}