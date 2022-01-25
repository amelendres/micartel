package com.micartel.telemetry.domain.builder

import com.micartel.telemetry.domain.*
import com.micartel.telemetry.domain.vehicle.Category
import com.micartel.telemetry.domain.vehicle.ChassisNumber
import com.micartel.telemetry.domain.assignTelemetryDevice

private const val CATEGORY_ELECTRIC = "CCAE"
private const val CATEGORY_HYBRID = "CCAI"
private const val CATEGORY_FUEL = "CCAD"
private val device: SerialNumber = SerialNumber("G-34567")

class TelemetricVehicleFactory {
    companion object {
        fun with(chassis: ChassisNumber, device: SerialNumber): TelemetricVehicle {
            return VehicleBuilder()
                .withChassis(chassis)
                .build()
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

        private fun create(): TelemetricVehicle {
            return VehicleBuilder()
                .build()
                .assignTelemetryDevice(device)
        }
        private fun withCategory(cat: Category): TelemetricVehicle {
            return VehicleBuilder()
                .withCategory(cat)
                .build()
                .assignTelemetryDevice(device)
        }
    }
}