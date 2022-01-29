package com.micartel.telemetry.application.command

import com.micartel.telemetry.domain.model.vehicle.*
import org.springframework.stereotype.Service

@Service
class RegisterVehicleHandler(private val vehicleRepository: VehicleRepository,
                             private val uniqueValidator: UniqueVehicleValidator
){
    fun handle(c: RegisterVehicleCommand) {
        val vehicle = Vehicle(
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