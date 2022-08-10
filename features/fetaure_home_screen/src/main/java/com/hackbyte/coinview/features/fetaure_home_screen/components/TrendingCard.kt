package com.hackbyte.coinview.features.fetaure_home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingCard(
    imageUrl: String,
    showPlaceHolder: Boolean = true,
    symbol: String,
    slug: String,
    price: Double,
    marketCapRank: String,
    onClick: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val cardWidth = (screenWidth / 2.5).dp
    ElevatedCard(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .width(cardWidth)
            .wrapContentHeight(),
        onClick = onClick
    ) {
        Text(
            text = "MarketCap rank $marketCapRank",
            fontSize = MaterialTheme.typography.labelMedium.fontSize,
            fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
            fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 5.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 15.dp, end = 15.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            AsyncImage(
                modifier = Modifier
                    .padding(5.dp)
                    .size(25.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Text(
                text = symbol,
                fontStyle = MaterialTheme.typography.labelLarge.fontStyle,
                fontSize = MaterialTheme.typography.labelLarge.fontSize,
                fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(start = 25.dp)
            )
        }
        Text(
            text = slug.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
            fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 15.dp)
        )
        Spacer(modifier = Modifier.padding(top = 7.5.dp))
        Text(
            text = price.toString().substring(0, 7),
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
            fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
            fontStyle = MaterialTheme.typography.titleSmall.fontStyle,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = 15.dp, bottom = 5.dp)
        )
    }
}


@Preview
@Composable
fun PreviewTrendingCard() {
    TrendingCard("dd", true, "hel", "hello", 100.0, "hello") {}
}