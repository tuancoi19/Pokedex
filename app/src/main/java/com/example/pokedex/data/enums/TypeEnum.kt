package com.example.pokedex.data.enums

import androidx.compose.ui.graphics.Color
import com.example.pokedex.data.enums.Sort.Name
import com.example.pokedex.ui.theme.typeBug
import com.example.pokedex.ui.theme.typeDark
import com.example.pokedex.ui.theme.typeDragon
import com.example.pokedex.ui.theme.typeElectric
import com.example.pokedex.ui.theme.typeFairy
import com.example.pokedex.ui.theme.typeFighting
import com.example.pokedex.ui.theme.typeFire
import com.example.pokedex.ui.theme.typeFlying
import com.example.pokedex.ui.theme.typeGhost
import com.example.pokedex.ui.theme.typeGrass
import com.example.pokedex.ui.theme.typeGround
import com.example.pokedex.ui.theme.typeIce
import com.example.pokedex.ui.theme.typeNormal
import com.example.pokedex.ui.theme.typePoison
import com.example.pokedex.ui.theme.typePsychic
import com.example.pokedex.ui.theme.typeRock
import com.example.pokedex.ui.theme.typeSteel
import com.example.pokedex.ui.theme.typeWater

enum class TypeEnum {
    Normal,
    Fighting,
    Flying,
    Ground,
    Poison,
    Rock,
    Bug,
    Ghost,
    Steel,
    Fire,
    Water,
    Grass,
    Electric,
    Psychic,
    Ice,
    Dragon,
    Dark,
    Fairy;

    val color: Color
        get() = when (this) {
            Normal -> typeNormal
            Fighting -> typeFighting
            Flying -> typeFlying
            Poison -> typePoison
            Ground -> typeGround
            Rock -> typeRock
            Bug -> typeBug
            Ghost -> typeGhost
            Steel -> typeSteel
            Fire -> typeFire
            Water -> typeWater
            Grass -> typeGrass
            Electric -> typeElectric
            Psychic -> typePsychic
            Ice -> typeIce
            Dragon -> typeDragon
            Dark -> typeDark
            Fairy -> typeFairy
        }

    companion object {
        fun fromValue(value: String): TypeEnum =
            TypeEnum.entries.first { it.toString().lowercase() == value }
    }
}