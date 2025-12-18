package com.example.pokedex.ui.screens.dex.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.pokedex.ui.component.PokemonCard
import com.example.pokedex.ui.theme.background
import com.example.pokedex.ui.theme.primary
import com.example.pokedex.ui.theme.white

@Composable
fun List() {
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(
                    horizontal = 12.dp,
                    vertical = 24.dp
                )
            ) {
                items(20) { item ->
                    PokemonCard()
                }
            }
        }
    }
}