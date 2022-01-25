package com.micartel.telemetry.domain

interface TelemetricVehicleRepository {
    fun save(vehicle: TelemetricVehicle)
    fun find(device: SerialNumber): TelemetricVehicle?
}