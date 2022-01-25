package com.micartel.telemetry.application.command

import com.micartel.telemetry.domain.TelemetricVehicleNotFoundException
import com.micartel.telemetry.domain.TelemetricVehicleRepository
import com.micartel.telemetry.domain.builder.TelemetricVehicleFactory
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.Mockito.*

internal class RegisterMileageTest {
    private val mockVehicleRepository: TelemetricVehicleRepository = mock(TelemetricVehicleRepository::class.java)
    private val handler: RegisterMileage = RegisterMileage(mockVehicleRepository)

    @Test
    fun `should register Mileage's`() {
        val vehicle = TelemetricVehicleFactory.hybrid()
        val command = MileageRequest(
            vehicle.telemetryDevice.toString(),
            1000,
            "km"
        )

        doReturn(vehicle).`when`(mockVehicleRepository).find(vehicle.telemetryDevice)
        doNothing().`when`(mockVehicleRepository).save(vehicle)

        handler.execute(command)

        verify(mockVehicleRepository).find(vehicle.telemetryDevice)
        verify(mockVehicleRepository).save(vehicle)
    }

    @ParameterizedTest
    @CsvSource(
        "-100, Km",
        "100, gr",
    )
    fun `should not allow register invalid mileage`(mileage:Int, unit:String) {
        val vehicle = TelemetricVehicleFactory.hybrid()
        val command = MileageRequest(
            vehicle.telemetryDevice.toString(),
            mileage,
            unit
        )

        doReturn(vehicle)
            .`when`(mockVehicleRepository).find(vehicle.telemetryDevice)

        assertThrows<IllegalArgumentException> {
            handler.execute(command)
        }
        verify(mockVehicleRepository).find(vehicle.telemetryDevice)
    }

    @Test
    fun `should not allow mileage registering of a non existing TelemetricVehicle's`() {
        val vehicle = TelemetricVehicleFactory.hybrid()
        val command = MileageRequest(
                vehicle.telemetryDevice.toString(),
                1000,
                "km"
        )

        doReturn(null)
            .`when`(mockVehicleRepository).find(vehicle.telemetryDevice)

        assertThrows<TelemetricVehicleNotFoundException> {
            handler.execute(command)
        }
        verify(mockVehicleRepository).find(vehicle.telemetryDevice)
    }
}