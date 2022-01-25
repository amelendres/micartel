package com.micartel.telemetry.domain.model

class Vehicle private constructor(
    val chassis: ChassisNumber,
    val licensePlate: LicensePlate,
    val brand: Brand,
    val category: Category,
    val inFleetDate: InFleetDate
){
    lateinit var telemetryDevice: SerialNumber
    lateinit var mileage: Mileage
    lateinit var batteryLevel: BatteryLevel
    lateinit var fuelLevel: FuelLevel

    companion object {
        fun register(
            chassis: ChassisNumber,
            licensePlate: LicensePlate,
            brand: Brand,
            category: Category,
            inFleetDate: InFleetDate
        ) = Vehicle(chassis, licensePlate, brand, category, inFleetDate)
    }

    fun assignTelemetryDevice(device:SerialNumber){
        telemetryDevice = device
    }

    fun isTelemetric():Boolean{
        return ::telemetryDevice.isInitialized
    }

    fun changeMileage(other: Mileage){
        if (!::mileage.isInitialized){
            mileage = other
            return
        }

        if (mileage.greaterThan(other)) throw CannotReduceMileageException(mileage, other)

        if (mileage.equals(other)) return

        mileage = other
    }

    fun changeBatteryLevel(level: BatteryLevel){
        if (!category.isElectric() and !category.isHybrid()) throw CannotChangeBatteryLevelToFuelVehicleException(chassis, category)

        if (!::batteryLevel.isInitialized){
            batteryLevel = level
            return
        }

        if (batteryLevel.equals(level)) return

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

        other as Vehicle
        return chassis == other.chassis
    }

    override fun hashCode(): Int {
        return chassis.hashCode()
    }
}