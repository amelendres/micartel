package com.micartel.telemetry.domain.vehicle

import com.micartel.telemetry.domain.builder.VehicleBuilder
import com.micartel.telemetry.domain.EntityTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VehicleTest : EntityTest<Vehicle> {
    override fun createEqualEntities() = Pair(
        VehicleBuilder().build(),
        VehicleBuilder().build()
    )

    override fun createNonEqualEntities() = Pair(
        VehicleBuilder()
            .withChassis(ChassisNumber("ChassisNumber1234"))
            .build(),
        VehicleBuilder()
            .withChassis(ChassisNumber("ChassisNumber5678"))
            .build()
    )

    @Test
    fun `should register Vehicle's`() {
        assertThat(VehicleBuilder().build()).isInstanceOf(com.micartel.telemetry.domain.vehicle.Vehicle::class.java)
    }
}