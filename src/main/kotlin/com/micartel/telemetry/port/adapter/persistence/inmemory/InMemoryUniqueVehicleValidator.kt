package com.micartel.telemetry.port.adapter.persistence.inmemory

import com.micartel.telemetry.domain.vehicle.UniqueVehicleValidator
import com.micartel.telemetry.domain.vehicle.Vehicle
import com.micartel.telemetry.domain.vehicle.VehicleAlreadyExistsException
import org.springframework.stereotype.Repository

@Repository
class InMemoryUniqueVehicleValidator(private val repo: InMemoryVehicleRepository) : UniqueVehicleValidator {
    override fun validate(vehicle: Vehicle) {
        repo.find(vehicle.chassis)?.also { throw VehicleAlreadyExistsException(vehicle.chassis) }
    }
}