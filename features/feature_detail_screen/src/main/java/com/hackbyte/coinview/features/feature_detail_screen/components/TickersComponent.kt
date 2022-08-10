package com.hackbyte.coinview.features.feature_detail_screen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.hackbyte.coinview.domain.models.coins.coins_id.Ticker


@Composable
fun TickerComponent(ticker: Ticker) {

    OutlinedCard(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 15.dp, end = 15.dp)
    ) {
        Text(
            text = "Pair ${ticker.base} / ${ticker.target}",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            style = MaterialTheme.typography.titleMedium,
            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
            fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
            modifier = Modifier.padding(start = 15.dp, top = 5.dp, end = 15.dp)
        )

        Spacer(modifier = Modifier.padding(top = 25.dp))
        FlowRow(
            Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        ) {
            Text(
                text = "Converted last in Btc ${ticker.converted_last.btc}",
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.padding(start = 15.dp))
            Text(
                text = "Converted last in Eth ${ticker.converted_last.eth}",
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.padding(start = 15.dp))
            Text(
                text = "Converted last in Usd ${ticker.converted_last.usd}",
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Spacer(modifier = Modifier.padding(top = 15.dp))
        FlowRow(
            Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        ) {
            Text(
                text = "Converted Volume in Btc ${ticker.converted_volume.btc}",
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.padding(start = 15.dp))
            Text(
                text = "Converted Volume in Eth ${ticker.converted_volume.eth}",
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.padding(start = 15.dp))
            Text(
                text = "Converted Volume in Usd ${ticker.converted_volume.usd}",
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Spacer(modifier = Modifier.padding(top = 15.dp))
        Text(
            text = "Market ${ticker.market.name} has trading incentive ${ticker.market.has_trading_incentive}",
            fontSize = MaterialTheme.typography.labelMedium.fontSize,
            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
            fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
            fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp)
        )
        Spacer(modifier = Modifier.padding(top = 15.dp))
        Text(
            text = "Bid ask ${ticker.bid_ask_spread_percentage} %",
            fontSize = MaterialTheme.typography.labelMedium.fontSize,
            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
            fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
            fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp)
        )
        FlowRow(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
            Text(text = "Last fetch at ${ticker.last_fetch_at}")
            Spacer(modifier = Modifier.padding(start = 15.dp))
            Text(text = "Last traded at ${ticker.last_traded_at}")
        }
        Spacer(modifier = Modifier.padding(bottom = 5.dp))
    }
}