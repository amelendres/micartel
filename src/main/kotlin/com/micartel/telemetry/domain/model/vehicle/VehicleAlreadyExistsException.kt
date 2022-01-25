package com.micartel.telemetry.domain.model.vehicle

data class VehicleAlreadyExistsException(val chassis: ChassisNumber)
    : RuntimeException("Vehicle $chassis already exists")
//    : DomainException
