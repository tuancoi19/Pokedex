package com.example.pokedex.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.pokedex.R
import com.example.pokedex.data.enums.TypeEnum
import com.example.pokedex.ui.theme.Body3
import com.example.pokedex.ui.theme.Caption
import com.example.pokedex.ui.theme.Headline
import com.example.pokedex.ui.theme.Subtitle1
import com.example.pokedex.ui.theme.Subtitle2
import com.example.pokedex.ui.theme.Subtitle3
import com.example.pokedex.ui.theme.dark
import com.example.pokedex.ui.theme.light
import com.example.pokedex.ui.theme.medium
import com.example.pokedex.ui.theme.white
import com.example.pokedex.ui.theme.wireframe
import com.example.pokedex.utils.capitalize
import com.example.pokedex.utils.formatNumber

@Composable
fun DetailsScreen(viewModel: DetailsVM) {
    LaunchedEffect(Unit) {
        viewModel.loadPokemonDetail(1)
    }

    val pokemonDetail = viewModel.pokemonDetail.value

    if (pokemonDetail == null) {
        Box {}
    } else {
        Scaffold(
            modifier = Modifier.safeDrawingPadding()
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        pokemonDetail?.types?.first()?.type?.typeEnum?.color
                            ?: wireframe
                    )
                    .padding(padding)
            ) {
                Background()

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    TopBar(
                        name = pokemonDetail?.name ?: "",
                        number = pokemonDetail?.number ?: ""
                    )
                    ImageSlider(image = pokemonDetail?.imageUrl)
                    TypeList(
                        type1 = pokemonDetail?.types[0]?.type?.typeEnum ?: TypeEnum.Normal,
                        type2 = pokemonDetail?.types[1]?.type?.typeEnum ?: TypeEnum.Normal
                    )
                    Spacer(Modifier.height(16.dp))
                    About(
                        color = pokemonDetail?.types[0]?.type?.typeEnum?.color ?: wireframe,
                        weight = pokemonDetail?.weight.toString(),
                        height = pokemonDetail?.height.toString(),
                        moves = pokemonDetail?.moves?.map { move -> move.move.name.capitalize("-") }
                            ?.take(2)
                            ?: listOf()
                    )
                    Spacer(Modifier.height(16.dp))
                    BaseStats(
                        color = pokemonDetail?.types[0]?.type?.typeEnum?.color ?: wireframe,
                        stats = pokemonDetail?.stats?.map { stat -> stat.baseStat } ?: listOf())
                }
            }
        }
    }
}

@Composable
private fun Background() {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.ic_poke_ball),
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(top = 8.dp, end = 8.dp)
                .size(208.dp),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                white.copy(alpha = 0.1f)
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(
                    start = 4.dp,
                    end = 4.dp,
                    bottom = 4.dp
                )
                .clip(RoundedCornerShape(8.dp))
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
                        offset = DpOffset(
                            x = 0.dp,
                            1.dp
                        )
                    )
                )

        )
    }
}

@Composable
private fun TopBar(name: String, number: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
            start = 20.dp,
            top = 20.dp,
            end = 20.dp,
            bottom = 24.dp
        )
    ) {
        Image(
            painter = painterResource(R.drawable.ic_arrow_back),
            modifier = Modifier.size(32.dp),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                white
            )
        )

        Spacer(Modifier.width(8.dp))

        Text(
            name,
            style = Headline,
            color = white,
            modifier = Modifier.weight(1f)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            number,
            style = Subtitle2,
            color = white,
        )
    }
}

@Composable
private fun ImageSlider(image: String? = null) {
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_chevron_left),
            modifier = Modifier.size(24.dp),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                white
            )
        )

        Spacer(Modifier.width(32.dp))

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
            modifier = Modifier.size(200.dp)
        )

        Spacer(Modifier.width(32.dp))

        Image(
            painter = painterResource(R.drawable.ic_chevron_right),
            modifier = Modifier.size(24.dp),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                white
            )
        )
    }
}

@Composable
private fun TypeList(
    type1: TypeEnum,
    type2: TypeEnum
) {
    Row(horizontalArrangement = Arrangement.Center) {
        Box(
            modifier = Modifier
                .height(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = type1.color, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                type1.toString(),
                style = Subtitle3,
                color = white
            )
        }

        Spacer(Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .height(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = type2.color, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                type2.toString(),
                style = Subtitle3,
                color = white
            )
        }
    }
}

@Composable
private fun About(
    color: Color = wireframe,
    weight: String = "9,9",
    height: String = "9,9",
    moves: List<String> = listOf("Ability 1", "Ability 2")
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            "About",
            style = Subtitle1,
            color = color
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Info(
                icon = R.drawable.ic_weight,
                value = "$weight kg",
                title = "Weight",
                modifier = Modifier.weight(1f)
            )
            VerticalDivider(thickness = 1.dp, color = light)
            Info(
                icon = R.drawable.ic_straighten,
                value = "$height m",
                title = "Height",
                modifier = Modifier.weight(1f)
            )
            VerticalDivider(thickness = 1.dp, color = light)
            Info(
                value = "${moves[0]}\n${moves[1]}",
                title = "Moves",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun Info(
    icon: Int? = null,
    title: String,
    value: String,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(32.dp)
                .fillMaxWidth()
        ) {
            if (icon != null) {
                Image(
                    painter = painterResource(icon),
                    modifier = Modifier.size(16.dp),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        dark
                    )
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    value,
                    style = Body3,
                    color = dark
                )
            } else {
                Text(
                    value,
                    style = Body3,
                    color = dark
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        Text(
            title,
            style = Caption,
            color = medium
        )
    }
}

@Composable
private fun BaseStats(stats: List<Int>, color: Color = wireframe) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            "Base Stats",
            style = Subtitle1,
            color = color
        )

        Spacer(Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Stat("HP", stats[0], color)
            Stat("ATK", stats[1], color)
            Stat("DEF", stats[2], color)
            Stat("SATK", stats[3], color)
            Stat("SDEF", stats[4], color)
            Stat("SPD", stats[5], color)
        }
    }
}

@Composable
private fun Stat(title: String, value: Int, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            title,
            style = Subtitle3,
            color = wireframe,
            textAlign = TextAlign.End,
            modifier = Modifier.width(28.dp)
        )
        Spacer(Modifier.width(12.dp))
        VerticalDivider(
            thickness = 1.dp,
            color = light
        )
        Spacer(Modifier.width(12.dp))
        Text(
            value.formatNumber(withHash = false),
            style = Body3,
            color = dark,
            modifier = Modifier.width(20.dp)
        )
        Spacer(Modifier.width(8.dp))
        LinearProgressIndicator(
            progress = { value.toFloat() / 250f },
            color = color,
            trackColor = color.copy(0.2f),
            drawStopIndicator = {},
            gapSize = 0.dp,
            modifier = Modifier
                .height(4.dp)
                .weight(1f),
        )
    }
}