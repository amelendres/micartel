package com.micartel.telemetry.domain

data class TelemetricVehicleNotFoundException(val device: String)
    : RuntimeException("Telemetric vehicle $device Not found")
