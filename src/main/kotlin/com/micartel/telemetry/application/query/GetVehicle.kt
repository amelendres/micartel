package com.micartel.telemetry.application.query

import com.micartel.telemetry.domain.vehicle.VehicleNotFoundException
import com.micartel.telemetry.domain.vehicle.*
import org.springframework.stereotype.Service

@Service
class GetVehicle(private val vehicleRepository: VehicleRepository){
    fun execute(chassis: String):Vehicle {
        val vehicle = vehicleRepository.find(ChassisNumber(chassis))
        requireNotNull(vehicle) { throw VehicleNotFoundException(chassis) }

        return vehicle
    }
}