package com.micartel.telemetry.domain

import com.micartel.telemetry.domain.vehicle.ChassisNumber

data class CannotConsumeBeforeRefuelException(val chassis: ChassisNumber)
    : RuntimeException("Vehicle $chassis, cannot consume before fuel")
