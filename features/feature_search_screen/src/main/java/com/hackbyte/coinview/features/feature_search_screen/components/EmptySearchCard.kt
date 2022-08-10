package com.hackbyte.coinview.features.feature_search_screen.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hackbyte.coinview.features.feature_search_screen.R


@Composable
fun EmptySearchCard() {
    var magnifyingGlassSize by remember {
        mutableStateOf(25.dp)
    }
    val magnifyingGlassSizeAnimation by animateDpAsState(
        targetValue = magnifyingGlassSize,
        animationSpec = spring(
            Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        )
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        ElevatedCard(
            modifier = Modifier
                .padding(50.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Explore the power search",
                    fontStyle = MaterialTheme.typography.displaySmall.fontStyle,
                    fontWeight = MaterialTheme.typography.displaySmall.fontWeight,
                    fontFamily = MaterialTheme.typography.displaySmall.fontFamily,
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(15.dp)
                )
                Spacer(modifier = Modifier.padding(12.5.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_magnifying_glass),
                    contentDescription = "magnifying glass",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(15.dp)
                        .size(magnifyingGlassSizeAnimation)
                )
            }
        }
    }
    SideEffect {
        magnifyingGlassSize = 200.dp
    }
}