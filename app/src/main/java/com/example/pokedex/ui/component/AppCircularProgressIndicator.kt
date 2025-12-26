package com.example.pokedex.ui.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pokedex.ui.theme.primary

@Composable
fun AppCircularProgressIndicator(color: Color = primary) {
    CircularProgressIndicator(
        color = color,
        strokeWidth = 4.dp
    )
}