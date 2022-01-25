package com.micartel.telemetry.domain.model

data class CannotChangeBatteryLevelToFuelVehicleException(val chassis: ChassisNumber, val category:Category)
    : RuntimeException("Cannot change battery level to fuel vehicle $chassis category $category")
