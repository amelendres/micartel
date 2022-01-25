package com.micartel.telemetry.domain.model

interface TelemetricVehicleRepository {
    fun save(vehicle: TelemetricVehicle)
    fun get(device: SerialNumber): TelemetricVehicle
}