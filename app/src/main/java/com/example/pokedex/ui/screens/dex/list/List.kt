package com.example.pokedex.ui.screens.dex.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedex.data.models.pokemon.Pokemon
import com.example.pokedex.ui.component.AppCircularProgressIndicator
import com.example.pokedex.ui.component.PokemonCard
import com.example.pokedex.ui.theme.primary
import com.example.pokedex.ui.theme.white
import com.example.pokedex.utils.capitalize

@Composable
fun List(
    pokemon: List<Pokemon>,
    onTap: (Int) -> Unit,
    onLoadMore: (Int?) -> Unit,
    onRefresh: () -> Unit,
    isLoadMore: Boolean = false,
    isRefresh: Boolean = false,
) {
    val gridState = rememberLazyGridState()
    val pullToRefreshState = rememberPullToRefreshState()

    LaunchedEffect(gridState) {
        snapshotFlow {
            gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastVisibleIndex ->
            if (!isRefresh) {
                onLoadMore(lastVisibleIndex)
            }
        }
    }


    PullToRefreshBox(
        onRefresh = onRefresh,
        isRefreshing = isRefresh,
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefresh,
                containerColor = white,
                color = primary,
                state = pullToRefreshState
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(
                horizontal = 12.dp,
                vertical = 24.dp
            )
        ) {
            items(
                pokemon.size, key = { index -> pokemon[index].id }) { index ->
                PokemonCard(
                    id = pokemon[index].id,
                    name = pokemon[index].name.capitalize(),
                    image = pokemon[index].imageUrl,
                    onTap = { onTap(pokemon[index].id) })
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                if (isLoadMore) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AppCircularProgressIndicator()
                    }
                }
            }
        }
    }
}