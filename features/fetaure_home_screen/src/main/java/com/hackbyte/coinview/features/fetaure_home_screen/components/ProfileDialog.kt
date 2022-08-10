package com.hackbyte.coinview.features.fetaure_home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.hackbyte.coinview.features.fetaure_home_screen.R
import timber.log.Timber


@Composable
fun ProfileDialog(
    username: String,
    setShowDialog: (Boolean) -> Unit,
    logout: () -> Unit
) {

    Timber.d("Show Dialog")
    Dialog(
        onDismissRequest = { setShowDialog(false) },
        properties = DialogProperties(true, true)
    ) {
        Card(shape = MaterialTheme.shapes.large) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier =
                Modifier.fillMaxWidth()
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { setShowDialog(false) }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "close"
                        )
                    }
                    Spacer(
                        modifier = Modifier.padding(
                            start = (
                                    LocalConfiguration.current.screenWidthDp / 5
                                    ).dp
                        )
                    )
                    Text(
                        text = "Profile",
                        fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.padding(top = 5.dp))
                Image(
                    painter = painterResource(id = R.drawable.profile_logo),
                    contentDescription = "profileImage",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(15.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.padding(top = 15.dp))
                Text(
                    text = username,
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.padding(top = 7.5.dp))
                TextButton(onClick = {
                    logout()
                }) {
                    Text(text = "Logout")
                }
            }
        }
    }

}


@Preview
@Composable
fun PreviewProfileDialog() {
}