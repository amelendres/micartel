package com.micartel.telemetry.domain.model

import com.micartel.telemetry.domain.model.vehicle.ChassisNumber

data class CannotConsumeBeforeRefuelException(val chassis: ChassisNumber)
    : RuntimeException("Vehicle $chassis, cannot consume before fuel")
