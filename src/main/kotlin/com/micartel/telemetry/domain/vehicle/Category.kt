package com.micartel.telemetry.domain.vehicle

data class Category (val value:String) {
    companion object {
        const val CATEGORY_LENGTH = 4

        private val cat = "[MNEHCDIJSRFGPULWOX]{1}".toRegex()
        private val type = "[BCDWVLSTFJXPQZEMRHYNGK]{1}".toRegex()
        private val trans = "[MNCABD]{1}".toRegex()
        private val fuelAir = "[RNDQHIECLSABMFVZUX]{1}".toRegex()
        private val hybrid = "[HI]{1}".toRegex()
        private val electric = "[EC]{1}".toRegex()
        private val categoryPatterns = arrayOf(cat, type, trans, fuelAir)
    }

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