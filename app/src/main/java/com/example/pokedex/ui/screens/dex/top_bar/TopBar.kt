package com.example.pokedex.ui.screens.dex.top_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.pokedex.R
import com.example.pokedex.data.enums.Sort
import com.example.pokedex.ui.theme.Body3
import com.example.pokedex.ui.theme.Headline
import com.example.pokedex.ui.theme.Subtitle2
import com.example.pokedex.ui.theme.dark
import com.example.pokedex.ui.theme.medium
import com.example.pokedex.ui.theme.primary
import com.example.pokedex.ui.theme.white

@Composable
fun TopBar(
    searchText: String,
    onTextChange: (String) -> Unit,
    sortType: Sort,
    toggleSortType: () -> Unit,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(color = primary)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Title()
            Spacer(Modifier.height(8.dp))
            SearchBar(
                searchText = searchText,
                onTextChange = onTextChange,
                sortType = sortType,
                toggleSortType = toggleSortType
            )
        }
    }
}

@Composable
private fun Title() {
    Row(
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
    searchText: String,
    onTextChange: (String) -> Unit,
    sortType: Sort,
    toggleSortType: () -> Unit,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.weight(1f)) {
            SearchField(
                text = searchText,
                onTextChange = onTextChange
            )
        }

        Spacer(Modifier.width(16.dp))

        SortButton(
            sortType = sortType,
            toggleSortType = toggleSortType
        )
    }
}

@Composable
private fun SearchField(
    text: String,
    onTextChange: (String) -> Unit
) {
    BasicTextField(
        value = text,
        onValueChange = { onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = white, shape = RoundedCornerShape(16.dp))
            .innerShadow(
                shape = RoundedCornerShape(16.dp), shadow = Shadow(
                    radius = 3.dp,
                    spread = 1.dp,
                    color = Color.Black.copy(alpha = 0.25f),
                    offset = DpOffset(x = 0.dp, 1.dp)
                )
            ),
        textStyle = Body3.copy(color = dark),
        singleLine = true,
        cursorBrush = SolidColor(dark),
        decorationBox = { innerTextField ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(Modifier.width(12.dp))

                Box(Modifier.padding(vertical = 8.dp)) {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                        modifier = Modifier.size(16.dp),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            primary
                        )
                    )
                }

                Spacer(Modifier.width(8.dp))

                Box(
                    Modifier
                        .padding(vertical = 8.dp)
                        .weight(1f)
                ) {
                    if (text.isEmpty()) Text(
                        text = "Search",
                        color = medium,
                        style = Body3,
                    )
                    innerTextField()
                }

                Spacer(Modifier.width(8.dp))

                if (text.isEmpty()) Spacer(Modifier.width(8.dp))
                else Box(
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                            end = 12.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                        .clickable(onClick = { onTextChange("") })
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_close),
                        modifier = Modifier.size(16.dp),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            primary
                        )
                    )
                }
            }
        },
    )
}

@Composable
fun SortButton(
    sortType: Sort,
    toggleSortType: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(
                    color = white, shape = RoundedCornerShape(16.dp)
                )
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
                imageVector = ImageVector.vectorResource(sortType.icon),
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
            shape = RoundedCornerShape(12.dp),
            containerColor = primary,
            modifier = Modifier.padding(4.dp)
        ) {
            Column {
                Text(
                    "Sort by:",
                    style = Subtitle2,
                    color = white,
                    modifier = Modifier.padding(
                        top = 12.dp,
                        start = 20.dp,
                        bottom = 16.dp
                    )
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
                    Column {
                        Sort.entries.forEachIndexed { index, sort ->
                            SortOptionItem(
                                type = sort,
                                isSelected = sort == sortType,
                                onClick = {
                                    if (sort != sortType) {
                                        toggleSortType()
                                        expanded = false
                                    }
                                }
                            )
                            if (index + 1 < Sort.entries.size) Spacer(Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SortOptionItem(
    type: Sort,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = isSelected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = primary,
                unselectedColor = primary,
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