package com.micartel.telemetry.domain.mother

import com.micartel.telemetry.domain.builder.VehicleRegistrar
import com.micartel.telemetry.domain.model.BatteryLevel
import com.micartel.telemetry.domain.model.FuelLevel
import com.micartel.telemetry.domain.model.Mileage
import com.micartel.telemetry.domain.model.Vehicle

class VehicleMother {
    companion object {
        fun withMileage(mileage: Mileage) : Vehicle {
            val vehicle = VehicleRegistrar().register()
            vehicle.changeMileage(mileage)
            return vehicle
        }
        fun withBatteryLevel(level: BatteryLevel) : Vehicle {
            val vehicle = VehicleRegistrar().register()
            vehicle.changeBatteryLevel(level)
            return vehicle
        }
        fun withFuel(fuel: FuelLevel) : Vehicle {
            val vehicle = VehicleRegistrar().register()
            vehicle.refuel(fuel)
            return vehicle
        }

    }
}