package com.example.pokedex.ui.screens.dex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.pokedex.data.enums.Error
import com.example.pokedex.data.enums.LoadStatus
import com.example.pokedex.ui.component.AppCircularProgressIndicator
import com.example.pokedex.ui.component.AppErrorDex
import com.example.pokedex.ui.screens.dex.list.List
import com.example.pokedex.ui.screens.dex.top_bar.TopBar
import com.example.pokedex.ui.theme.primary
import com.example.pokedex.ui.theme.white

@Composable
fun DexScreen(
    viewModel: DexVM,
    onNavigateToDetail: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.loadPokemon()
    }

    Scaffold(
        topBar = {
            TopBar(
                searchText = viewModel.searchText.value,
                onTextChange = { value -> viewModel.setSearchText(value) },
                sortType = viewModel.sortType.value,
                toggleSortType = { viewModel.toggleSortType() })
        },
        modifier = Modifier.safeDrawingPadding()
    ) { padding ->
        Box(Modifier.padding(padding)) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color = primary)
                    .padding(
                        start = 4.dp,
                        end = 4.dp,
                        top = 8.dp,
                        bottom = 4.dp
                    )
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(
                            color = white,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .innerShadow(
                            shape = RoundedCornerShape(8.dp),
                            shadow = Shadow(
                                radius = 3.dp,
                                spread = 1.dp,
                                color = Color.Black.copy(alpha = 0.25f),
                                offset = DpOffset(x = 0.dp, 1.dp)
                            )
                        )
                ) {
                    when (viewModel.loadStatus.value) {
                        LoadStatus.Loading -> Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            AppCircularProgressIndicator()
                        }

                        LoadStatus.Error -> AppErrorDex(onRetry = { viewModel.loadPokemon() })

                        LoadStatus.Success ->
                            if (viewModel.pokemon.isEmpty()) AppErrorDex(
                                type = Error.empty,
                                onRetry = { viewModel.loadPokemon() })
                            else List(
                                pokemon = viewModel.pokemon,
                                onTap = onNavigateToDetail,
                                isLoadMore = viewModel.isLoadMore.value,
                                isRefresh = viewModel.loadStatus.value == LoadStatus.Loading,
                                onLoadMore = { lastIndex ->
                                    if (lastIndex != null &&
                                        lastIndex >= viewModel.pokemon.size - 3
                                    ) {
                                        viewModel.loadMorePokemon()
                                    }
                                },
                                onRefresh = { viewModel.loadPokemon() }
                            )

                        else -> {}
                    }
                }
            }
        }
    }
}