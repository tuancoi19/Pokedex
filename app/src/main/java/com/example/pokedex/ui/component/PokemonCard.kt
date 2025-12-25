package com.example.pokedex.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.pokedex.R
import com.example.pokedex.ui.theme.Body3
import com.example.pokedex.ui.theme.Caption
import com.example.pokedex.ui.theme.background
import com.example.pokedex.ui.theme.dark
import com.example.pokedex.ui.theme.medium
import com.example.pokedex.ui.theme.white
import com.example.pokedex.utils.formatNumber

@Composable
fun PokemonCard(
    id: Int,
    name: String = "Pokémon Name",
    image: String? = null,
    onTap: () -> Unit = {}
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(104f / 108f)
            .dropShadow(
                shape = RoundedCornerShape(8.dp),
                shadow = Shadow(
                    radius = 3.dp,
                    spread = 1.dp,
                    color = Color.Black.copy(alpha = 0.2f),
                    offset = DpOffset(
                        x = 0.dp,
                        1.dp
                    )
                )
            )
            .clip(RoundedCornerShape(8.dp))
            .background(color = white, shape = RoundedCornerShape(8.dp))
            .clickable(
                onClick = onTap
            )

    ) {
        Text(
            id.formatNumber(),
            color = medium,
            style = Caption,
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(top = 4.dp, end = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.41f)
                .align(Alignment.BottomCenter)
                .background(color = background)
                .clip(
                    RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = 8.dp
                    )
                )
        )

        Text(
            name,
            color = dark,
            style = Body3,
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 4.dp
                )
        )

        AsyncImage(
            model = image,
            contentDescription = null,
            imageLoader = remember {
                ImageLoader.Builder(context)
                    .placeholder(R.drawable.ic_poke_placeholder)
                    .error(R.drawable.ic_poke_placeholder)
                    .crossfade(true)
                    .build()
            },
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth(0.69f)
                .aspectRatio(1f)
                .align(Alignment.Center),
        )
    }
}
