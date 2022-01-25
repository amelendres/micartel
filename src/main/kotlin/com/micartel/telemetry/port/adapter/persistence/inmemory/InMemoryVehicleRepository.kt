package com.micartel.telemetry.port.adapter.persistence.inmemory

import com.micartel.telemetry.domain.model.vehicle.ChassisNumber
import com.micartel.telemetry.domain.model.vehicle.Vehicle
import com.micartel.telemetry.domain.model.vehicle.VehicleRepository
import org.springframework.stereotype.Component

@Component
class InMemoryVehicleRepository(private val vehicles: MutableMap<ChassisNumber, Vehicle> = HashMap()) :
    VehicleRepository {

    override fun save(vehicle: Vehicle) {
        vehicles[vehicle.chassis] = vehicle
    }

    override fun find(chassis: ChassisNumber): Vehicle? {
        return vehicles[chassis]
    }
}