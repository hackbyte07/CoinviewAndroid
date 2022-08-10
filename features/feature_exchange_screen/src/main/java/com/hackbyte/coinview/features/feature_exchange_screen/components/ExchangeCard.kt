package com.hackbyte.coinview.features.feature_exchange_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun ExchangeCard(
    imageUrl: String,
    name: String,
    trustScoreRank: String,
    description: String,
    tradeVolume24hrs: Double
) {
    val width = LocalConfiguration.current.screenWidthDp
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 15.dp, end = 15.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "image",
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.padding(start = (width / 4).dp))
            Text(
                text = name,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.padding(start = 15.dp))
            if (trustScoreRank.toInt() != 0) {
                Text(
                    text = trustScoreRank,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                    fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                    fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        Text(
            text = "Trade Volume 24hrs Btc $tradeVolume24hrs",
            fontSize = MaterialTheme.typography.labelMedium.fontSize,
            fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp)
        )
        Spacer(modifier = Modifier.padding(top = 15.dp))
        Text(
            text = description,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
        )
    }
}

@Preview
@Composable
fun PreviewExchangeCard() {
    ExchangeCard(
        imageUrl = "https://assets.coingecko.com/markets/images/52/small/binance.jpg?1519353250",
        name = "hello",
        trustScoreRank = "20",
        description = "${LoremIpsum()}",
        tradeVolume24hrs = 2666.0
    )
}