package com.micartel.telemetry.domain.model

import com.micartel.telemetry.domain.model.vehicle.Category
import com.micartel.telemetry.domain.model.vehicle.ChassisNumber

data class TelemetricVehicle(
    val chassis: ChassisNumber,
    val category: Category,
    val telemetryDevice: SerialNumber
){
    lateinit var mileage: Mileage
    lateinit var batteryLevel: BatteryLevel
    lateinit var fuelLevel: FuelLevel

    fun changeMileage(other: Mileage){
        if (::mileage.isInitialized && mileage.greaterThan(other)) throw CannotReduceMileageException(mileage, other)

        if (::mileage.isInitialized && mileage.equals(other)) return

        mileage = other
    }

    fun changeBatteryLevel(level: BatteryLevel){
        if (!category.isElectric() and !category.isHybrid()) throw CannotChangeBatteryLevelToFuelVehicleException(chassis, category)

        if (::batteryLevel.isInitialized && batteryLevel.equals(level)) return

        batteryLevel = level
    }

    fun refuel(qty: FuelLevel){
        if(category.isElectric()) throw CannotRefuelElectricVehicleException(chassis, category)

        fuelLevel = if (::fuelLevel.isInitialized) fuelLevel.increment(qty) else qty
    }

    fun consumeFuel(qty: FuelLevel){
        if (!::fuelLevel.isInitialized) throw CannotConsumeBeforeRefuelException(chassis)

        fuelLevel = fuelLevel.decrement(qty)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TelemetricVehicle
        return chassis == other.chassis
                && telemetryDevice == other.telemetryDevice

    }

    override fun hashCode(): Int {
        return chassis.hashCode()
    }
}