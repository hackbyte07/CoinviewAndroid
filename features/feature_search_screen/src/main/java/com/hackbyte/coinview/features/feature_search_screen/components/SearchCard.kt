package com.hackbyte.coinview.features.feature_search_screen.components

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
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCard(
    name: String,
    symbol: String,
    image: String,
    isLoading: Boolean = true,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(7.5.dp),
        onClick = onClick
    ) {
        Row(
            Modifier
                .padding(15.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .placeholder(
                        visible = isLoading,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color.LightGray
                    ),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "image"
            )
            Spacer(modifier = Modifier.padding(start = 15.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = symbol.uppercase(),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.padding(top = 15.dp))
                Text(
                    text = name,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                    fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                    fontStyle = MaterialTheme.typography.titleSmall.fontStyle,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearchCard() {
    SearchCard(
        name = "Bitcoin",
        symbol = "btc",
        image = "https://assets.coingecko.com/nft_contracts/images/531/thumb/jadu-hoverboard.png",
        isLoading = false
    ) {

    }
}

