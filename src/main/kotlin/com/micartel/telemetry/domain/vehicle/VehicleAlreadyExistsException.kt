package com.micartel.telemetry.domain.vehicle

data class VehicleAlreadyExistsException(val chassis: ChassisNumber)
    : RuntimeException("Vehicle $chassis already exists")
//    : DomainException
