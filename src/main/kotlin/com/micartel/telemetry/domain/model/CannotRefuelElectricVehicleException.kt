package com.micartel.telemetry.domain.model

data class CannotRefuelElectricVehicleException(val chassis: ChassisNumber, val category:Category)
    : RuntimeException("Cannot refuel electric vehicle $chassis category $category")
