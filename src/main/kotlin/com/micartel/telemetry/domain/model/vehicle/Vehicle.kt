package com.micartel.telemetry.domain.model.vehicle

data class Vehicle constructor(
    val chassis: ChassisNumber,
    val licensePlate: LicensePlate,
    val brand: Brand,
    val category: Category,
    val inFleetDate: InFleetDate
){
    companion object {
        fun register(
            chassis: ChassisNumber,
            licensePlate: LicensePlate,
            brand: Brand,
            category: Category,
            inFleetDate: InFleetDate
        ) = Vehicle(chassis, licensePlate, brand, category, inFleetDate)
    }

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