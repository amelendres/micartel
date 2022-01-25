package com.micartel.telemetry.domain.model.vehicle

interface UniqueVehicleValidator {
    fun validate(vehicle: Vehicle)
}