package com.micartel.telemetry.domain

data class Mileage(val value:Int, val unit:MileageUnit ){
    init {
        require(value >= 0) {
            "The Mileage $value should not be negative"
        }
    }

    override fun toString(): String {
        return "$value $unit"
    }

    fun greaterThan(other:Mileage):Boolean{
        return value > other.value && unit == other.unit
    }
}

enum class MileageUnit {
    KM,
    MILE
}
