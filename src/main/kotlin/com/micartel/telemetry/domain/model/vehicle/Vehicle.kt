package com.micartel.telemetry.domain.model.vehicle

data class Vehicle(
    val chassis: ChassisNumber,
    val licensePlate: LicensePlate,
    val brand: Brand,
    val category: Category,
    val inFleetDate: InFleetDate
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vehicle
        return chassis == other.chassis
    }

    override fun hashCode(): Int {
        return chassis.hashCode()
    }
}