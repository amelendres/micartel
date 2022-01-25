package com.micartel.telemetry.domain.model.vehicle

interface VehicleRepository {
    fun save(vehicle: Vehicle)
    fun find(chassis: ChassisNumber): Vehicle?
}