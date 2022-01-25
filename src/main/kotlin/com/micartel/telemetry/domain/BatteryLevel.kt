package com.micartel.telemetry.domain

data class BatteryLevel(val value:Int) {
    companion object {
        const val BATTERY_MIN_LEVEL = 0
        const val BATTERY_MAX_LEVEL = 100
    }
    init {
        require(value >= BATTERY_MIN_LEVEL ) {
            "The BatteryLevel $value should not be lower than $BATTERY_MIN_LEVEL"
        }
        require(value <= BATTERY_MAX_LEVEL) {
            "The BatteryLevel $value should not be greater than $BATTERY_MAX_LEVEL"
        }
    }

    override fun toString(): String {
        return value.toString()
    }
}