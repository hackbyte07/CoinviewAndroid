package com.hackbyte.coinview.features.common.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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


@Composable
fun ErrorScreen(message: String) {
    var warningImageSize by remember {
        mutableStateOf(25.dp)
    }
    val warningImageAnimateSize by animateDpAsState(
        targetValue = warningImageSize,
        animationSpec = spring(
            Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        )
    )
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {

        ElevatedCard() {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = message,
                    fontStyle = MaterialTheme.typography.displaySmall.fontStyle,
                    fontWeight = MaterialTheme.typography.displaySmall.fontWeight,
                    fontFamily = MaterialTheme.typography.displaySmall.fontFamily,
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(15.dp)
                )
                Spacer(modifier = Modifier.padding(12.5.dp))
                Image(
                    painter = painterResource(id = com.hackbyte.coinview.features.common.R.drawable.ic_warning),
                    contentScale = ContentScale.Crop,
                    contentDescription = "warning",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(warningImageAnimateSize)
                )

            }
        }
    }
    SideEffect {
        warningImageSize = 200.dp
    }
}


