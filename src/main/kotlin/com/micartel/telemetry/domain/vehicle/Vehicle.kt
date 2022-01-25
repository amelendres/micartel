package com.micartel.telemetry.domain.vehicle

data class Vehicle(
        val chassis: ChassisNumber,
        val licensePlate: LicensePlate,
        val brand: Brand,
        val category: Category,
        val inFleetDate: InFleetDate
){}