package com.micartel.telemetry.domain

import com.micartel.telemetry.domain.vehicle.Category
import com.micartel.telemetry.domain.vehicle.ChassisNumber

data class CannotChangeBatteryLevelToFuelVehicleException(val chassis: ChassisNumber, val category: Category)
    : RuntimeException("Cannot change battery level to fuel vehicle $chassis category $category")
