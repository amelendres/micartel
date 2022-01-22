package com.micartel.telemetry.domain.builder

import com.micartel.telemetry.domain.model.*
import java.time.LocalDateTime

class VehicleRegistrar {
    var chassis: ChassisNumber = ChassisNumber("ChassisNumber1234")
    var licensePlate: LicensePlate = LicensePlate("1234ABC")
    var brand: Brand = Brand("Toyota")
    var category: Category = Category("CCMN")
    var inFleetDate: InFleetDate = InFleetDate(LocalDateTime.now())

    fun withChassis(chassisNumber: ChassisNumber): VehicleRegistrar {
        chassis = chassisNumber
        return this
    }

    fun register() : Vehicle {
        return Vehicle.register(
            chassis,
            licensePlate,
            brand,
            category,
            inFleetDate
        )
    }
}