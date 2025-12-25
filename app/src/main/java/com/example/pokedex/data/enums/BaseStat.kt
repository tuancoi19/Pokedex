package com.example.pokedex.data.enums

enum class BaseStat(val value: String) {
    HP("hp"),
    ATK("attack"),
    DEF("defense"),
    SATK("special-attack"),
    SDEF("special-defense"),
    SPD("speed");

    companion object {
        fun fromValue(value: String): BaseStat =
            BaseStat.entries.first { it.value == value }
    }
}