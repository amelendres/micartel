package com.micartel.telemetry.port.adapter.persistence.inmemory

import com.micartel.telemetry.domain.vehicle.ChassisNumber
import com.micartel.telemetry.domain.vehicle.Vehicle
import com.micartel.telemetry.domain.vehicle.VehicleRepository
import org.springframework.stereotype.Repository

@Repository
class InMemoryVehicleRepository(private val vehicles: MutableMap<ChassisNumber, Vehicle> = HashMap()) :
    VehicleRepository {

    override fun save(vehicle: Vehicle) {
        vehicles[vehicle.chassis] = vehicle
    }

    override fun find(chassis: ChassisNumber): Vehicle? {
        return vehicles[chassis]
    }
}