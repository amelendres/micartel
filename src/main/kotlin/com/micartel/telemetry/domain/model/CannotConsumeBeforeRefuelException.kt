package com.micartel.telemetry.domain.model

data class CannotConsumeBeforeRefuelException(val chassis: ChassisNumber)
    : RuntimeException("Vehicle $chassis, cannot consume before fuel")
