package com.micartel.telemetry.domain.builder

import com.micartel.telemetry.domain.vehicle.*
import java.time.LocalDateTime

class VehicleBuilder {
    var chassis: ChassisNumber = ChassisNumber("ChassisNumber1234")
    var licensePlate: LicensePlate = LicensePlate("1234ABC")
    var brand: Brand = Brand("Toyota")
    var category: Category = Category("CCMN")

    companion object{
        var inFleetDate: InFleetDate = InFleetDate(LocalDateTime.now())
    }

    fun withChassis(chassisNumber: ChassisNumber): VehicleBuilder {
        chassis = chassisNumber
        return this
    }

    fun withCategory(cat: Category): VehicleBuilder {
        category = cat
        return this
    }

    fun build() : Vehicle {
        return Vehicle(
                chassis,
                licensePlate,
                brand,
                category,
                inFleetDate
        )
    }
}