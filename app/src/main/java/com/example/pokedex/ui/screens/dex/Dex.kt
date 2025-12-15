package com.example.pokedex.ui.screens.dex

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokedex.R
import com.example.pokedex.ui.screens.dex.top_bar.TopBar
import com.example.pokedex.ui.theme.Headline
import com.example.pokedex.ui.theme.white

@Composable
fun Dex() {
    Scaffold(topBar = { TopBar() }) {
    }
}