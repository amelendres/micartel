package com.micartel.telemetry.domain.model

import com.micartel.telemetry.domain.mother.VehicleMother
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TelemetricVehicleTest : EntityTest<TelemetricVehicle> {
    override fun createEqualEntities() = Pair(
        VehicleMother.electric(),
        VehicleMother.electric(),
    )

    override fun createNonEqualEntities() = Pair(
        VehicleMother.electric(),
        VehicleMother.with(ChassisNumber("ChassisNumber5678"), SerialNumber("A-12345")),
    )

    @Test
    fun `should add mileage`() {
        val vehicle = VehicleMother.electric()
        val mileage = Mileage(100, MileageUnit.KM)

        vehicle.changeMileage(mileage)
        assertThat(vehicle.mileage).isEqualTo(mileage)
    }

    @Test
    fun `should not allow to reduce mileage`() {
        val mileage = Mileage(10, MileageUnit.KM)
        val vehicle = VehicleMother.withMileage(mileage)
        val other = Mileage(9, MileageUnit.KM)

        assertThrows<CannotReduceMileageException> {
            vehicle.changeMileage(other)
        }
    }

    @Test
    fun `should change battery level`() {
        val batteryLevel = BatteryLevel(0)
        val vehicle = VehicleMother.withBatteryLevel(batteryLevel)
        val other = BatteryLevel(1)

        vehicle.changeBatteryLevel(other)
        assertThat(vehicle.batteryLevel).isEqualTo(other)
    }

    @Test
    fun `should not allow to change BatteryLevel for non hybrid or electric Vehicle's`() {
        val fuelVehicle = VehicleMother.fuel()
        val batteryLevel = BatteryLevel(1)

        assertThrows<CannotChangeBatteryLevelToFuelVehicleException> {
            fuelVehicle.changeBatteryLevel(batteryLevel)
        }
    }

    @Test
    fun `should refuel`() {
        val fuelLevel = FuelLevel(50)
        val vehicle = VehicleMother.withFuel(fuelLevel)
        val other = FuelLevel(20)

        vehicle.refuel(other)
        assertThat(vehicle.fuelLevel.value).isEqualTo(70)
    }

    @Test
    fun `should consume fuel`() {
        val fuelLevel = FuelLevel(50)
        val vehicle = VehicleMother.withFuel(fuelLevel)
        val other = FuelLevel(20)

        vehicle.consumeFuel(other)
        assertThat(vehicle.fuelLevel.value).isEqualTo(30)
    }

    @Test
    fun `should not allow negative FuelLevel`() {
        val fuelLevel = FuelLevel(50)
        val vehicle = VehicleMother.withFuel(fuelLevel)
        val other = FuelLevel(51)

        assertThrows<IllegalArgumentException> {
            vehicle.consumeFuel(other)
        }
    }

    @Test
    fun `should not allow consume fuel before refuel`() {
        val vehicle = VehicleMother.fuel()
        val fuelLevel = FuelLevel(1)

        assertThrows<CannotConsumeBeforeRefuelException> {
            vehicle.consumeFuel(fuelLevel)
        }
    }

    @Test
    fun `should not allow to refuel Electric Vehicle's`() {
        val electricVehicle = VehicleMother.electric()
        val fuelLevel = FuelLevel(100)

        assertThrows<CannotRefuelElectricVehicleException> {
            electricVehicle.refuel(fuelLevel)
        }
    }
}