package com.micartel.telemetry.port.adapter.persistence.inmemory

import com.micartel.telemetry.domain.model.*
import com.micartel.telemetry.domain.model.SerialNumber
import org.springframework.stereotype.Component

@Component
class InMemoryTelemetricVehicleRepository(private val vehicles: MutableMap<SerialNumber, TelemetricVehicle> = HashMap()) : TelemetricVehicleRepository {

    override fun save(vehicle: TelemetricVehicle) {
        vehicles[vehicle.telemetryDevice] = vehicle
    }

    override fun get(device: SerialNumber): TelemetricVehicle {
        return vehicles[device] ?: throw TelemetricVehicleNotFound(device)
    }
}