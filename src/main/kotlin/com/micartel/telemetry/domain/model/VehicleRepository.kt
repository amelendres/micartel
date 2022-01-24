package com.micartel.telemetry.domain.model

interface VehicleRepository {
    fun save(vehicle: Vehicle)
    fun find(chassis: ChassisNumber): Vehicle?
    fun getByTelemetryDevice(device: SerialNumber): Vehicle
}