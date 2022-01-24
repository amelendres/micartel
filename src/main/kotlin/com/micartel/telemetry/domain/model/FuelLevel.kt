package com.micartel.telemetry.domain.model

data class FuelLevel(val value:Int) {
    init {
        require(value >= 0 ) {
            "The FuelLevel $value should not be negative"
        }
    }

    override fun toString(): String {
        return value.toString()
    }

    fun increment(other:FuelLevel):FuelLevel{
        return FuelLevel(value + other.value)
    }

    fun decrement(other:FuelLevel):FuelLevel{
        return FuelLevel(value - other.value)
    }
}