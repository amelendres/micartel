package com.micartel.telemetry.domain.service

import com.micartel.telemetry.domain.builder.VehicleRegistrar
import com.micartel.telemetry.domain.model.SerialNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AssignTelemetryDeviceTest {

    @Test
    fun `should assign telemetry device`() {
        val vehicle = VehicleRegistrar().register()
        val device = SerialNumber("G-34567")

        val telemetricVehicle = vehicle.assignTelemetryDevice(device)

        assertThat(telemetricVehicle.telemetryDevice).isEqualTo(device)
    }
}