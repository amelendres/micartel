package com.micartel.telemetry.domain.model

val cat = "[MNEHCDIJSRFGPULWOX]{1}".toRegex()
val type = "[BCDWVLSTFJXPQZEMRHYNGK]{1}".toRegex()
val trans = "[MNCABD]{1}".toRegex()
val fuel = "[RNDQHIECLSABMFVZUX]{1}".toRegex()

val categoryPatterns = arrayOf(cat,type,trans,fuel)

data class Category (val value:String) {
    init {
        value.forEachIndexed { index, c -> require(categoryPatterns[index].matches(c.toString())){
            "The Category $value at position ${index+1} does not match the pattern ${categoryPatterns[index]}."
        } }
    }

    override fun toString(): String {
        return value
    }
}