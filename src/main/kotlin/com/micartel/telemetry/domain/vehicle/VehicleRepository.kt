package com.micartel.telemetry.domain.vehicle

interface VehicleRepository {
    fun save(vehicle: Vehicle)
    fun find(chassis: ChassisNumber): Vehicle?
}