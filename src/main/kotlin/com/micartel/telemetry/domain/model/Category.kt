package com.micartel.telemetry.domain.model

const val CATEGORY_LENGTH = 4

val cat = "[MNEHCDIJSRFGPULWOX]{1}".toRegex()
val type = "[BCDWVLSTFJXPQZEMRHYNGK]{1}".toRegex()
val trans = "[MNCABD]{1}".toRegex()
val fuelAir = "[RNDQHIECLSABMFVZUX]{1}".toRegex()
val hybrid = "[HI]{1}".toRegex()
val electric = "[EC]{1}".toRegex()
val categoryPatterns = arrayOf(cat,type,trans,fuelAir)

data class Category (val value:String) {
    init {
        require(value.length == CATEGORY_LENGTH) {
            "The Category $value length should be $CATEGORY_LENGTH"
        }

        value.forEachIndexed { index, c -> require(categoryPatterns[index].matches(c.toString())){
            "The Category $value at position ${index+1} does not match the pattern ${categoryPatterns[index]}."
        } }
    }

    override fun toString(): String {
        return value
    }

    fun isElectric():Boolean{
        return electric.matches(value.last().toString())
    }

    fun isHybrid():Boolean{
        return hybrid.matches(value.last().toString())
    }
}