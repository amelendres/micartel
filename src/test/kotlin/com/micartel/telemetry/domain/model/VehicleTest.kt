package com.micartel.telemetry.domain.model

import com.micartel.telemetry.domain.builder.VehicleRegistrar
import com.micartel.telemetry.domain.model.vehicle.ChassisNumber
import com.micartel.telemetry.domain.model.vehicle.Vehicle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
}