package com.hackbyte.coinview.features.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hackbyte.coinview.features.authentication.components.LoadingBar
import com.hackbyte.coinview.features.authentication.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SignupScreen(
    navigator: DestinationsNavigator
) {
    val viewModel = getViewModel() as SignupViewModel
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val localKeyBoard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val screenHeight = LocalConfiguration.current.screenHeightDp
    Box(Modifier.fillMaxSize()) {
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(Modifier.wrapContentHeight()) {
                Image(
                    painter = painterResource(
                        if (isSystemInDarkTheme())
                            R.drawable.blockchain_night
                        else
                            R.drawable.blockchain
                    ),
                    contentDescription = "background image",
                    modifier = Modifier
                        .height((screenHeight / 3).dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                IconButton(onClick = {
                    navigator.navigate(LoginScreenDestination) {
                        launchSingleTop = true
                    }
                }) {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "icon")
                }

            }

            Text(
                text = "Signup",
                fontStyle = MaterialTheme.typography.displayMedium.fontStyle,
                fontWeight = MaterialTheme.typography.displayMedium.fontWeight,
                fontFamily = MaterialTheme.typography.displayMedium.fontFamily,
                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                style = MaterialTheme.typography.displayMedium,
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "usernameIcon"
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                shape = ShapeDefaults.ExtraLarge
            )
            Spacer(modifier = Modifier.padding(top = 25.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "emailIcon"
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                shape = ShapeDefaults.ExtraLarge
            )
            Spacer(modifier = Modifier.padding(top = 25.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus(true)
                        localKeyBoard?.hide()
                    }
                ),
                shape = ShapeDefaults.ExtraLarge
            )
            Spacer(modifier = Modifier.padding(top = 50.dp))
            Button(onClick = {

                scope.launch {
                    viewModel.signup(username, email, password)
                    if (viewModel.authResponse) {
                        snackbarHostState.showSnackbar("Signup Successful")
                        navigator.navigate(LoginScreenDestination) {
                            launchSingleTop = true
                        }
                        return@launch
                    }
                }
            }) {
                Text(text = "Signup")
            }
        }
        LoadingBar(isLoading = viewModel.isLoading)
    }
    SnackbarHost(hostState = snackbarHostState)
}