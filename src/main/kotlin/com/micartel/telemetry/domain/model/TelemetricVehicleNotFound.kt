package com.micartel.telemetry.domain.model

data class TelemetricVehicleNotFound(val device: SerialNumber)
    : RuntimeException("Telemetric vehicle $device Not found")
//    : NotFoundException
