package com.example.pokedex.ui.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.pokedex.ui.theme.primary

@Composable
fun AppCircularProgressIndicator() {
    CircularProgressIndicator(
        color = primary,
        strokeWidth = 4.dp
    )
}