package com.example.pokedex.ui.screens.dex.top_bar

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.pokedex.R
import com.example.pokedex.data.enum.Sort
import com.example.pokedex.ui.theme.Body3
import com.example.pokedex.ui.theme.Headline
import com.example.pokedex.ui.theme.Subtitle2
import com.example.pokedex.ui.theme.dark
import com.example.pokedex.ui.theme.medium
import com.example.pokedex.ui.theme.primary
import com.example.pokedex.ui.theme.white

@Composable
fun TopBar() {
    Box(
        Modifier
            .fillMaxWidth()
            .background(color = primary)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Title()
            Spacer(Modifier.height(8.dp))
            SearchBar()
        }
    }
}

@Composable
private fun Title() {
    Row(
        modifier = Modifier.padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_poke_ball),
            modifier = Modifier.size(24.dp),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                white
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = "Pokédex",
            color = white,
            style = Headline,
        )
    }
}

@Composable
private fun SearchBar(

) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.weight(1f)) {
            SearchField(onValueChange = {})
        }

        Spacer(Modifier.width(16.dp))

        SortButton()
    }
}

@Composable
private fun SearchField(
    value: String = "",
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .innerShadow(
                shape = RoundedCornerShape(16.dp),
                shadow = Shadow(
                    radius = 3.dp,
                    spread = 1.dp,
                    color = Color.Black.copy(alpha = 0.25f),
                    offset = DpOffset(x = 0.dp, 1.dp)
                )
            )
            .clip(shape = RoundedCornerShape(16.dp))
            .background(
                color = white,
                shape = RoundedCornerShape(16.dp)
            ),
        singleLine = true,
        textStyle = Body3,
        placeholder = {
            Text(
                text = "Search",
                color = medium,
                style = Body3,
            )
        },
        leadingIcon = {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                modifier = Modifier.size(16.dp),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    primary
                )
            )
        },
        suffix = {
            if (!value.isEmpty()) Button(onClick = {}) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                    modifier = Modifier.size(16.dp),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        primary
                    )
                )
            }
        },
        colors = TextFieldDefaults.colors().copy(
            cursorColor = dark,
            focusedTextColor = dark,
            unfocusedTextColor = dark,
            textSelectionColors = TextSelectionColors(
                handleColor = dark,
                backgroundColor = TextFieldDefaults.colors().textSelectionColors.backgroundColor
            )
        ),
    )
}

@Composable
fun SortButton() {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = white)
            .innerShadow(
                shape = RoundedCornerShape(16.dp),
                shadow = Shadow(
                    radius = 3.dp,
                    spread = 1.dp,
                    color = Color.Black.copy(alpha = 0.25f),
                    offset = DpOffset(x = 0.dp, 1.dp)
                )
            )
            .clickable {
                expanded = !expanded
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_number),
            modifier = Modifier.size(16.dp),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                primary
            )
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        Modifier
            .background(color = primary, shape = RoundedCornerShape(12.dp))
            .padding(4.dp)
    ) {
        Column {
            Text(
                "Sort by:",
                style = Subtitle2,
                color = white,
                modifier = Modifier.padding(top = 12.dp, start = 20.dp, bottom = 16.dp)
            )

            Box(
                Modifier
                    .background(color = white, shape = RoundedCornerShape(8.dp))
                    .innerShadow(
                        shape = RoundedCornerShape(16.dp), shadow = Shadow(
                            radius = 3.dp,
                            spread = 1.dp,
                            color = Color.Black.copy(alpha = 0.25f),
                            offset = DpOffset(x = 0.dp, 1.dp)
                        )
                    )
                    .background(color = white, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Sort.entries.forEach { sort -> SortOptionItem(type = sort, isSelected = false) }
                }
            }
        }
    }
}

@Composable
fun SortOptionItem(
    type: Sort,
    isSelected: Boolean
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = isSelected,
            onClick = {},
            colors = RadioButtonDefaults.colors(
                selectedColor = primary,
            ),
            modifier = Modifier.size(16.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            type.title,
            style = Body3,
            color = dark,
            modifier = Modifier.weight(1f)
        )
    }
}