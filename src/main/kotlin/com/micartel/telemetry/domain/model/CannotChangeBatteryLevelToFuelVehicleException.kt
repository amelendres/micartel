package com.micartel.telemetry.domain.model

import com.micartel.telemetry.domain.model.vehicle.Category
import com.micartel.telemetry.domain.model.vehicle.ChassisNumber

data class CannotChangeBatteryLevelToFuelVehicleException(val chassis: ChassisNumber, val category: Category)
    : RuntimeException("Cannot change battery level to fuel vehicle $chassis category $category")
