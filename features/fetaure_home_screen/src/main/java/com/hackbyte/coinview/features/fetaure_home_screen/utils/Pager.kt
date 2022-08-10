package com.hackbyte.coinview.features.fetaure_home_screen.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*


@Composable
fun LazyListState.OnBottomReached(
    buffer: Int = 0,
    loadMore: () -> Unit
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem =
                this.layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true
            lastVisibleItem.index == layoutInfo.totalItemsCount - 1 - buffer
        }
    }
    LaunchedEffect(key1 = shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }.collect {
            if (it) loadMore()
        }
    }
}