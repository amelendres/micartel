package com.micartel.telemetry.domain.model

const val BATTERY_MIN_LEVEL = 0
const val BATTERY_MAX_LEVEL = 100
data class BatteryLevel(val value:Int) {
    init {
        require(value >= BATTERY_MIN_LEVEL ) {
            "The BatteryLevel $value should not be lower than $BATTERY_MIN_LEVEL"
        }
        require(value <= BATTERY_MAX_LEVEL) {
            "The BatteryLevel $value should not be greater than $BATTERY_MAX_LEVEL"
        }
    }

    override fun toString(): String {
        return "$value"
    }
}