package com.micartel.telemetry.domain.vehicle

interface UniqueVehicleValidator {
    fun validate(vehicle: Vehicle)
}