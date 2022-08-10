package com.hackbyte.coinview.features.fetaure_home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketDataCard(
    currentPrice: Double,
    image: String,
    marketCapRank: Int,
    name: String,
    priceChange24hrsPercentage: Double,
    symbol: String,
    isLoading: Boolean = true,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 7.5.dp, end = 7.5.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = "coin",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .placeholder(highlight = PlaceholderHighlight.shimmer(), visible = isLoading),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(start = 25.dp))
            Text(
                text = marketCapRank.toString(),
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.padding(start = 15.dp))
            Text(
                text = calculatePercentage(priceChange24hrsPercentage),
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                style = MaterialTheme.typography.labelMedium,
                color = percentageColor(priceChange24hrsPercentage)
            )
            Spacer(modifier = Modifier.padding(start = 15.dp))
            Text(
                text = symbol.uppercase(Locale.getDefault()),
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.padding(start = 25.dp))
            Column {
                Text(
                    text = name,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                    fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                    fontStyle = MaterialTheme.typography.titleSmall.fontStyle,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.padding(top = 7.5.dp))
                Text(
                    text = "$ $currentPrice",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                    style = MaterialTheme.typography.titleMedium
                )

            }

        }
    }

}


private fun calculatePercentage(priceChange24hrsPercentage: Double): String {
    val price = priceChange24hrsPercentage.toString()
    return if (price.startsWith("-"))
        "$price %"
    else if (price.startsWith("0"))
        "$price %"
    else
        "+$price %"
}

private fun percentageColor(pricePercentage24Hrs: Double): Color {
    val price = pricePercentage24Hrs.toString()
    return if (price.startsWith("-")) Color.Red
    else if (price.startsWith("0")) Color.Yellow
    else
        Color.Green
}

@Preview
@Composable
fun PreviewMarketDataCard() {
    MarketDataCard(
        currentPrice = 234.0,
        image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        marketCapRank = 1,
        name = "Bitcoin",
        priceChange24hrsPercentage = 1.77355,
        symbol = "btc"
    ) {

    }
}