package com.micartel.telemetry.domain.vehicle

data class VehicleNotFoundException(val chassis: String)
    : RuntimeException("Vehicle $chassis not found")
