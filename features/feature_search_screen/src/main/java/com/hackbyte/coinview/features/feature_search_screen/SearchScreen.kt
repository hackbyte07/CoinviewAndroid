package com.hackbyte.coinview.features.feature_search_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackbyte.coinview.features.common.components.BackHandler
import com.hackbyte.coinview.features.common.components.BottomTransition
import com.hackbyte.coinview.features.common.components.CircularLoading
import com.hackbyte.coinview.features.common.components.ErrorScreen
import com.hackbyte.coinview.features.feature_detail_screen.destinations.DetailScreenDestination
import com.hackbyte.coinview.features.feature_search_screen.components.EmptySearchCard
import com.hackbyte.coinview.features.feature_search_screen.components.SearchCard
import com.hackbyte.coinview.features.feature_search_screen.destinations.SearchScreenDestination
import com.hackbyte.coinview.features.feature_search_screen.utils.Search
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import timber.log.Timber


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Destination(style = BottomTransition::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = getViewModel(),
    navigator: DestinationsNavigator
) {

    var searchQuery by remember { mutableStateOf("") }
    val keyboard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val searchCoinDataState = viewModel.searchCoinsDataState
    val searchCoinsData = viewModel.searchCoinsDataState.data
    val scope = rememberCoroutineScope()
    val snackBarHostState = SnackbarHostState()
    Timber.d("creating screen Search Screen")

    Scaffold(
        topBar = {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 5.dp),
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = {
                    Text(
                        text = "Search...",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "search"
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(50.dp),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.searchCoins(searchQuery)
                        keyboard?.hide()
                        focusManager.clearFocus()
                    }
                ),
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = {
                            searchQuery = ""
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = "close"
                            )
                        }
                    }
                }
            )

        }
    ) {
        when (viewModel.networkState.hasNetwork) {
            true -> {
                when (viewModel.search) {
                    Search.TRUE -> {
                        BackHandler {
                            viewModel.setSearch()
                        }
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2), modifier = Modifier.padding(
                                top = it.calculateTopPadding() + 15.dp,
                                start = 7.5.dp,
                                end = 7.5.dp
                            )
                        ) {
                            items(searchCoinsData.coins) { item ->
                                SearchCard(
                                    name = item.name,
                                    symbol = item.symbol,
                                    image = item.thumb,
                                    isLoading = searchCoinDataState.isLoading
                                ) {
                                    val id = item.id
                                    if (id.isEmpty() || id.isBlank()) {
                                        scope.launch { snackBarHostState.showSnackbar("No more data available") }
                                        return@SearchCard
                                    }
                                    navigator.navigate(DetailScreenDestination(id)) {
                                        launchSingleTop = true
                                        popUpTo(SearchScreenDestination)
                                    }
                                }
                            }
                            items(searchCoinsData.exchanges) { item ->
                                SearchCard(
                                    name = item.name,
                                    symbol = item.market_type,
                                    image = item.thumb,
                                    isLoading = searchCoinDataState.isLoading
                                ) {
                                    scope.launch { snackBarHostState.showSnackbar("No more data available") }
                                    return@SearchCard

                                }
                            }

                            items(searchCoinsData.nfts) { item ->
                                SearchCard(
                                    name = item.name,
                                    symbol = item.symbol,
                                    image = item.thumb,
                                    isLoading = searchCoinDataState.isLoading
                                ) {
                                    scope.launch { snackBarHostState.showSnackbar("Currently no data is availbale for nfts ") }
                                    return@SearchCard
                                }
                            }
                        }
                    }
                    Search.FALSE -> {
                        EmptySearchCard()
                    }

                }
            }
            false -> {
                ErrorScreen(message = "No network")
            }
        }
    }

    CircularLoading(isLoading = searchCoinDataState.isLoading)
    SnackbarHost(hostState = snackBarHostState)
}


@Preview
@Composable
fun PreviewSearchScreen() {

}
