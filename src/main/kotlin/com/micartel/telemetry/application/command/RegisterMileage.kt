package com.micartel.telemetry.application.command

import com.micartel.telemetry.domain.*
import com.micartel.telemetry.domain.SerialNumber

class RegisterMileage(private val vehicleRepository: TelemetricVehicleRepository){
    fun execute(cmd: MileageRequest) {
        val vehicle = vehicleRepository.find(SerialNumber(cmd.serialNumber))
        requireNotNull(vehicle) { throw TelemetricVehicleNotFoundException(cmd.serialNumber) }

        vehicle.changeMileage(Mileage(cmd.mileage, MileageUnit.valueOf(cmd.unit.uppercase())))
        vehicleRepository.save(vehicle)
    }
}