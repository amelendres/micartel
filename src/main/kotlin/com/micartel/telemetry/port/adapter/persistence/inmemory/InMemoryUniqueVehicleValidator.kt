package com.micartel.telemetry.port.adapter.persistence.inmemory

import com.micartel.telemetry.domain.model.*
import org.springframework.stereotype.Component

@Component
class InMemoryUniqueVehicleValidator(private val repo: InMemoryVehicleRepository) : UniqueVehicleValidator {
    override fun validate(vehicle: Vehicle) {
        if (repo.find(vehicle.chassis)!=null){
            throw VehicleAlreadyExistsException(vehicle.chassis)
        }
    }
}