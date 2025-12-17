package com.example.pokedex.data.enums

import com.example.pokedex.data.enums.Sort.Name

enum class Stat(val value: String) {
    HP("hp"),
    ATK("attack"),
    DEF("defense"),
    SATK("special-attack"),
    SDEF("special-defense"),
    SPD("speed");

    companion object {
        fun fromValue(value: String): Stat =
            Stat.entries.first { it.value == value }
    }
}