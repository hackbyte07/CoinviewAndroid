package com.hackbyte.coinview.features.common.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun CircularLoading(isLoading: Boolean = false, background: Color = Color.Transparent) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

