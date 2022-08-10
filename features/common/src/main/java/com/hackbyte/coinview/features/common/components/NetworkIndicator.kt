package com.hackbyte.coinview.features.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun NetworkIndicator() {
    Row(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .height(50.dp)
            .padding(start = 15.dp, end = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Network unavailable",
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
            fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
            fontStyle = MaterialTheme.typography.labelLarge.fontStyle,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.padding(start = 25.dp))
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "close",
            tint = MaterialTheme.colorScheme.error
        )
    }
}