package com.micartel.telemetry.domain

data class CannotReduceMileageException(val current: Mileage, val to: Mileage)
    : RuntimeException("Cannot reduce mileage $current to $to")