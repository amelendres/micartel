package com.micartel.telemetry.application.command

import com.micartel.telemetry.domain.model.*
import com.micartel.telemetry.domain.model.SerialNumber

class RegisterMileageHandler(private val vehicleRepository: TelemetricVehicleRepository){
    fun handle(c: RegisterMileageCommand) {
        val vehicle = vehicleRepository.get(SerialNumber(c.serialNumber))

        vehicle.changeMileage(Mileage(c.mileage, MileageUnit.valueOf(c.unit.uppercase())))
        vehicleRepository.save(vehicle)
    }
}