package com.micartel.telemetry.domain.model

import com.micartel.telemetry.domain.model.vehicle.Category
import com.micartel.telemetry.domain.model.vehicle.ChassisNumber

data class CannotRefuelElectricVehicleException(val chassis: ChassisNumber, val category: Category)
    : RuntimeException("Cannot refuel electric vehicle $chassis category $category")
