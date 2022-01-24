package com.micartel.telemetry.domain.mother

import com.micartel.telemetry.domain.builder.VehicleRegistrar
import com.micartel.telemetry.domain.model.*

const val CATEGORY_ELECTRIC = "CCAE"
const val CATEGORY_HYBRID = "CCAI"
const val CATEGORY_FUEL = "CCAD"
class VehicleMother {
    companion object {
        fun withMileage(mileage: Mileage) : Vehicle {
            val vehicle = VehicleRegistrar().register()
            vehicle.changeMileage(mileage)
            return vehicle
        }
        fun withBatteryLevel(level: BatteryLevel) : Vehicle {
            val vehicle = this.electric()
            vehicle.changeBatteryLevel(level)
            return vehicle
        }
        fun withFuel(fuel: FuelLevel) : Vehicle {
            val vehicle = this.hybrid()
            vehicle.refuel(fuel)
            return vehicle
        }
        fun fuel() : Vehicle {
            return VehicleRegistrar()
                .withCategory(Category(CATEGORY_FUEL))
                .register()
        }
        fun electric() : Vehicle {
            return VehicleRegistrar()
                .withCategory(Category(CATEGORY_ELECTRIC))
                .register()
        }
        fun hybrid() : Vehicle {
            return VehicleRegistrar()
                            .withCategory(Category(CATEGORY_HYBRID))
                            .register()
        }
    }
}