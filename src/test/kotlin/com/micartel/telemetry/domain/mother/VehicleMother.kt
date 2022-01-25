package com.micartel.telemetry.domain.mother

import com.micartel.telemetry.domain.builder.VehicleRegistrar
import com.micartel.telemetry.domain.model.*
import com.micartel.telemetry.domain.service.assignTelemetryDevice

const val CATEGORY_ELECTRIC = "CCAE"
const val CATEGORY_HYBRID = "CCAI"
const val CATEGORY_FUEL = "CCAD"
val device:SerialNumber = SerialNumber("G-34567")

class VehicleMother {
    companion object {
        fun with(chassis:ChassisNumber, device:SerialNumber): TelemetricVehicle{
            return VehicleRegistrar()
                .withChassis(chassis)
                .register()
                .assignTelemetryDevice(device)
        }
        fun fuel() : TelemetricVehicle {
            return this.withCategory(Category(CATEGORY_FUEL))
        }
        fun electric() : TelemetricVehicle {
            return this.withCategory(Category(CATEGORY_ELECTRIC))
        }
        fun hybrid() : TelemetricVehicle {
            return this.withCategory(Category(CATEGORY_HYBRID))
        }
        fun withMileage(mileage: Mileage) : TelemetricVehicle {
            val vehicle = this.create()
            vehicle.changeMileage(mileage)
            return vehicle
        }
        fun withBatteryLevel(level: BatteryLevel) : TelemetricVehicle {
            val vehicle = this.electric()
            vehicle.changeBatteryLevel(level)
            return vehicle
        }
        fun withFuel(fuel: FuelLevel) : TelemetricVehicle {
            val vehicle = this.hybrid()
            vehicle.refuel(fuel)
            return vehicle
        }

        private fun create(): TelemetricVehicle{
            return VehicleRegistrar()
                .register()
                .assignTelemetryDevice(device)
        }
        private fun withCategory(cat:Category): TelemetricVehicle{
            return VehicleRegistrar()
                .withCategory(cat)
                .register()
                .assignTelemetryDevice(device)
        }
    }
}