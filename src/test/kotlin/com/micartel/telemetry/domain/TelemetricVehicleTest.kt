package com.micartel.telemetry.domain

import com.micartel.telemetry.domain.vehicle.ChassisNumber
import com.micartel.telemetry.domain.builder.TelemetricVehicleFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TelemetricVehicleTest : EntityTest<TelemetricVehicle> {
    override fun createEqualEntities() = Pair(
        TelemetricVehicleFactory.electric(),
        TelemetricVehicleFactory.electric(),
    )

    override fun createNonEqualEntities() = Pair(
        TelemetricVehicleFactory.electric(),
        TelemetricVehicleFactory.with(ChassisNumber("ChassisNumber5678"), SerialNumber("A-12345")),
    )

    @Test
    fun `should add mileage`() {
        val vehicle = TelemetricVehicleFactory.electric()
        val mileage = Mileage(100, MileageUnit.KM)

        vehicle.changeMileage(mileage)
        assertThat(vehicle.mileage).isEqualTo(mileage)
    }

    @Test
    fun `should not allow to reduce mileage`() {
        val mileage = Mileage(10, MileageUnit.KM)
        val vehicle = TelemetricVehicleFactory.withMileage(mileage)
        val other = Mileage(9, MileageUnit.KM)

        assertThrows<CannotReduceMileageException> {
            vehicle.changeMileage(other)
        }
    }

    @Test
    fun `should change battery level`() {
        val batteryLevel = BatteryLevel(0)
        val vehicle = TelemetricVehicleFactory.withBatteryLevel(batteryLevel)
        val other = BatteryLevel(1)

        vehicle.changeBatteryLevel(other)
        assertThat(vehicle.batteryLevel).isEqualTo(other)
    }

    @Test
    fun `should not allow to change BatteryLevel for non hybrid or electric Vehicle's`() {
        val fuelVehicle = TelemetricVehicleFactory.fuel()
        val batteryLevel = BatteryLevel(1)

        assertThrows<CannotChangeBatteryLevelToFuelVehicleException> {
            fuelVehicle.changeBatteryLevel(batteryLevel)
        }
    }

    @Test
    fun `should refuel`() {
        val fuelLevel = FuelLevel(50)
        val vehicle = TelemetricVehicleFactory.withFuel(fuelLevel)
        val other = FuelLevel(20)

        vehicle.refuel(other)
        assertThat(vehicle.fuelLevel.value).isEqualTo(70)
    }

    @Test
    fun `should consume fuel`() {
        val fuelLevel = FuelLevel(50)
        val vehicle = TelemetricVehicleFactory.withFuel(fuelLevel)
        val other = FuelLevel(20)

        vehicle.consumeFuel(other)
        assertThat(vehicle.fuelLevel.value).isEqualTo(30)
    }

    @Test
    fun `should not allow negative FuelLevel`() {
        val fuelLevel = FuelLevel(50)
        val vehicle = TelemetricVehicleFactory.withFuel(fuelLevel)
        val other = FuelLevel(51)

        assertThrows<IllegalArgumentException> {
            vehicle.consumeFuel(other)
        }
    }

    @Test
    fun `should not allow consume fuel before refuel`() {
        val vehicle = TelemetricVehicleFactory.fuel()
        val fuelLevel = FuelLevel(1)

        assertThrows<CannotConsumeBeforeRefuelException> {
            vehicle.consumeFuel(fuelLevel)
        }
    }

    @Test
    fun `should not allow to refuel Electric Vehicle's`() {
        val electricVehicle = TelemetricVehicleFactory.electric()
        val fuelLevel = FuelLevel(100)

        assertThrows<com.micartel.telemetry.domain.CannotRefuelElectricVehicleException> {
            electricVehicle.refuel(fuelLevel)
        }
    }
}