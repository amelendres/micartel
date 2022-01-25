package com.micartel.telemetry.application.command

import com.micartel.telemetry.domain.model.SerialNumber
import com.micartel.telemetry.domain.model.TelemetricVehicleNotFound
import com.micartel.telemetry.domain.model.TelemetricVehicleRepository
import com.micartel.telemetry.domain.mother.TelemetricVehicleMother
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.Mockito.*

internal class RegisterMileageHandlerTest {
    private val mockVehicleRepository: TelemetricVehicleRepository = mock(TelemetricVehicleRepository::class.java)
    private val handler: RegisterMileageHandler = RegisterMileageHandler(mockVehicleRepository)

    @Test
    fun `should register Mileage's`() {
        val vehicle = TelemetricVehicleMother.hybrid()
        val command = RegisterMileageCommand(
            vehicle.telemetryDevice.toString(),
            1000,
            "km"
        )

        doReturn(vehicle).`when`(mockVehicleRepository).get(vehicle.telemetryDevice)
        doNothing().`when`(mockVehicleRepository).save(vehicle)

        handler.handle(command)

        verify(mockVehicleRepository).get(vehicle.telemetryDevice)
        verify(mockVehicleRepository).save(vehicle)
    }

    @ParameterizedTest
    @CsvSource(
        "-100, Km",
        "100, gr",
    )
    fun `should not allow register invalid mileage`(mileage:Int, unit:String) {
        val vehicle = TelemetricVehicleMother.hybrid()
        val command = RegisterMileageCommand(
            vehicle.telemetryDevice.toString(),
            mileage,
            unit
        )

        doReturn(vehicle)
            .`when`(mockVehicleRepository).get(vehicle.telemetryDevice)

        assertThrows<IllegalArgumentException> {
            handler.handle(command)
        }
        verify(mockVehicleRepository).get(vehicle.telemetryDevice)
    }

    @Test
    fun `should not allow mileage registering of a non existing TelemetricVehicle's`() {
        val device = SerialNumber("G-34567")
        val command = RegisterMileageCommand(
            device.toString(),
            100,
            "km"
        )

        doThrow(TelemetricVehicleNotFound::class.java)
            .`when`(mockVehicleRepository).get(device)

        assertThrows<TelemetricVehicleNotFound> {
            handler.handle(command)
        }
        verify(mockVehicleRepository).get(device)
    }
}