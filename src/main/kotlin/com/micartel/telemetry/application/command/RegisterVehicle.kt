package com.micartel.telemetry.application.command

import com.micartel.telemetry.domain.vehicle.*
import org.springframework.stereotype.Service

@Service
class RegisterVehicle(private val vehicleRepository: VehicleRepository,
                      private val uniqueValidator: UniqueVehicleValidator
){
    fun execute(cmd: VehicleRequest) {
        val vehicle = Vehicle(
                ChassisNumber(cmd.chassisNumber),
                LicensePlate(cmd.licensePlate),
                Brand(cmd.brand),
                Category(cmd.category),
                InFleetDate.fromString(cmd.inFleetDate)
        )
        uniqueValidator.validate(vehicle)
        vehicleRepository.save(vehicle)
    }
}