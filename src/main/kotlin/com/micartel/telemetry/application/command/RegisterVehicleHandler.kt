package com.micartel.telemetry.application.command

import com.micartel.telemetry.domain.model.vehicle.*
import org.springframework.stereotype.Component

@Component
class RegisterVehicleHandler(private val vehicleRepository: VehicleRepository,
                             private val uniqueValidator: UniqueVehicleValidator
){
    fun handle(c: RegisterVehicleCommand) {
        val vehicle = Vehicle.register(
            ChassisNumber(c.chassisNumber),
            LicensePlate(c.licensePlate),
            Brand(c.brand),
            Category(c.category),
            InFleetDate.fromString(c.inFleetDate)
        )
        uniqueValidator.validate(vehicle)
        vehicleRepository.save(vehicle)
    }
}