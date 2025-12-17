package com.example.pokedex.ui.screens.dex

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pokedex.ui.screens.dex.list.List
import com.example.pokedex.ui.screens.dex.top_bar.TopBar

@Composable
fun DexScreen(
    viewModel: DexVM
) {
    Scaffold(
        topBar = {
            TopBar(
                searchText = viewModel.searchText.value,
                onTextChange = { value -> viewModel.setSearchText(value) },
                sortType = viewModel.sortType.value,
                toggleSortType = { viewModel.toggleSortType() }
            )
        },
        modifier = Modifier.safeDrawingPadding()
    ) { _ -> List() }
}