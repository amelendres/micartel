package com.micartel.telemetry.domain.model

import com.micartel.telemetry.domain.builder.VehicleRegistrar
import com.micartel.telemetry.domain.mother.VehicleMother
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class VehicleTest : EntityTest<Vehicle> {
    override fun createEqualEntities() = Pair(
        VehicleRegistrar().register(),
        VehicleRegistrar().register()
    )

    override fun createNonEqualEntities() = Pair(
        VehicleRegistrar()
            .withChassis(ChassisNumber("ChassisNumber1234"))
            .register(),
        VehicleRegistrar()
            .withChassis(ChassisNumber("ChassisNumber5678"))
            .register()
    )

    @Test
    fun `should register Vehicle's`() {
        assertThat(VehicleRegistrar().register()).isInstanceOf(Vehicle::class.java)
    }

    @Test
    fun `should assign telemetry device`() {
        val device = SerialNumber("G-34567")
        val vehicle = VehicleRegistrar().register()

        vehicle.assignTelemetryDevice(device)

        assertThat(vehicle.telemetryDevice).isEqualTo(device)
    }

    @Test
    fun `should add mileage`() {
        val vehicle = VehicleRegistrar().register()
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
        val vehicle = VehicleRegistrar().register()
        val fuelLevel = FuelLevel(1)

        assertThrows<CannotConsumeBeforeRefuelException> {
            vehicle.consumeFuel(fuelLevel)
        }
    }

}