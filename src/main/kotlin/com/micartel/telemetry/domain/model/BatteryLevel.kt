package com.micartel.telemetry.domain.model

const val batteryMinLevel = 0
const val batteryMaxLevel = 100
data class BatteryLevel(val value:Int) {
    init {
        require(value >= batteryMinLevel ) {
            "The BatteryLevel $value should not be lower than $batteryMinLevel"
        }
        require(value <= batteryMaxLevel) {
            "The BatteryLevel $value should not be greater than $batteryMaxLevel"
        }
    }

    override fun toString(): String {
        return value.toString()
    }
}