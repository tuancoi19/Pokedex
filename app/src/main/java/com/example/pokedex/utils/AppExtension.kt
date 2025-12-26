package com.example.pokedex.utils

fun String.capitalize(join: String = " "): String {
    if (this.isEmpty()) return this
    return this
        .lowercase()
        .split(join)
        .joinToString(join) { word ->
            word.replaceFirstChar { it.uppercase() }
        }
}

fun Int.formatNumber(withHash: Boolean = true): String {
    val number = this.toString()

    return buildString {
        if (withHash) append('#')
        append(number.padStart(3, '0'))
    }
}

fun Int.formatMeasureIndex(): String {
    val number = this.toString()

    if (number.length <= 1) return "0,$number"
    val index = number.length - 1
    return number.take(index) + "," + number.substring(index)
}