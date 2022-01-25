package com.micartel.telemetry.domain

import com.micartel.telemetry.domain.builder.VehicleBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AssignTelemetryDeviceTest {

    @Test
    fun `should assign telemetry device`() {
        val vehicle = VehicleBuilder().build()
        val device = SerialNumber("G-34567")

        val telemetricVehicle = vehicle.assignTelemetryDevice(device)

        assertThat(telemetricVehicle.telemetryDevice).isEqualTo(device)
    }
}