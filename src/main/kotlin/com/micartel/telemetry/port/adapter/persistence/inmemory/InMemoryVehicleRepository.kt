package com.micartel.telemetry.port.adapter.persistence.inmemory

import com.micartel.telemetry.domain.model.*
import org.springframework.stereotype.Component

@Component
class InMemoryVehicleRepository(private val vehicles: MutableMap<ChassisNumber, Vehicle> = HashMap()) : VehicleRepository {
    private val deviceIdx:MutableMap<SerialNumber,ChassisNumber> = HashMap()

    override fun save(vehicle: Vehicle) {
        vehicles[vehicle.chassis] = vehicle
        if (vehicle.isTelemetric()) deviceIdx[vehicle.telemetryDevice] = vehicle.chassis
    }

    override fun find(chassis: ChassisNumber): Vehicle? {
        return vehicles[chassis]
    }

    override fun getByTelemetryDevice(device: SerialNumber): Vehicle {
        val chassis = deviceIdx[device] ?: throw TelemetricVehicleNotFound(device)
        return vehicles[chassis] ?: throw TelemetricVehicleNotFound(device)
    }
}