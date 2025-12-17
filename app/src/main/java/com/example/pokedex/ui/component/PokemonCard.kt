package com.example.pokedex.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.pokedex.R
import com.example.pokedex.ui.theme.Body3
import com.example.pokedex.ui.theme.Caption
import com.example.pokedex.ui.theme.background
import com.example.pokedex.ui.theme.dark
import com.example.pokedex.ui.theme.medium
import com.example.pokedex.ui.theme.white

@Composable
fun PokemonCard(
    id: Int = 999,
    name: String = "Pokémon Name",
    image: String? = null,
    onTap: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .dropShadow(
                shape = RoundedCornerShape(20.dp),
                shadow = Shadow(
                    radius = 3.dp,
                    spread = 1.dp,
                    color = Color.Black.copy(alpha = 0.2f),
                    offset = DpOffset(x = 0.dp, 1.dp)
                )
            )
            .width(104.dp)
            .height(108.dp)
            .background(color = white, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))

    ) {
        Text(
            "#$id",
            color = medium,
            style = Caption,
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(top = 4.dp, end = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .align(Alignment.BottomCenter)
                .background(color = background)
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
        )

        Text(
            name,
            color = dark,
            style = Body3,
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(start = 8.dp, end = 8.dp, bottom = 4.dp)
        )

        Image(
            painter = painterResource(R.drawable.ic_poke_placeholder),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .align(Alignment.Center),
        )
    }
}
