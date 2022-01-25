package com.micartel.telemetry.port.adapter.persistence.inmemory

import com.micartel.telemetry.domain.*
import com.micartel.telemetry.domain.SerialNumber
import org.springframework.stereotype.Repository

@Repository
class InMemoryTelemetricVehicleRepository(private val vehicles: MutableMap<SerialNumber, TelemetricVehicle> = HashMap()) : TelemetricVehicleRepository {

    override fun save(vehicle: TelemetricVehicle) {
        vehicles[vehicle.telemetryDevice] = vehicle
    }

    override fun find(device: SerialNumber): TelemetricVehicle? {
        return vehicles[device]
    }
}