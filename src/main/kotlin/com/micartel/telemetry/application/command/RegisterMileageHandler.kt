package com.micartel.telemetry.application.command

import com.micartel.telemetry.domain.model.*

class RegisterMileageHandler(private val vehicleRepository: VehicleRepository){
    fun handle(c: RegisterMileageCommand) {
        var vehicle = vehicleRepository.getByTelemetryDevice(SerialNumber(c.serialNumber))
        vehicle.changeMileage(Mileage(c.mileage, MileageUnit.KM))
        vehicleRepository.save(vehicle)
    }
}