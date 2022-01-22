package com.micartel.telemetry.domain.model

interface VehicleRepository {
    fun save(vehicle: Vehicle)
}