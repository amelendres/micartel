package com.micartel.telemetry.application.command

import com.micartel.telemetry.domain.model.*

class RegisterMileageHandler(private val vehicleRepository: TelemetricVehicleRepository){
    fun handle(c: RegisterMileageCommand) {
        val vehicle = vehicleRepository.get(SerialNumber(c.serialNumber))

        vehicle.changeMileage(Mileage(c.mileage, MileageUnit.KM))
        vehicleRepository.save(vehicle)
    }
}