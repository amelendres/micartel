package com.micartel.telemetry.domain

import com.micartel.telemetry.domain.vehicle.Category
import com.micartel.telemetry.domain.vehicle.ChassisNumber

data class CannotRefuelElectricVehicleException(val chassis: ChassisNumber, val category: Category)
    : RuntimeException("Cannot refuel electric vehicle $chassis category $category")
