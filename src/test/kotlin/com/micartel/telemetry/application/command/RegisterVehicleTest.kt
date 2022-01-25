package com.micartel.telemetry.application.command

import com.micartel.telemetry.domain.builder.VehicleBuilder
import com.micartel.telemetry.domain.vehicle.UniqueVehicleValidator
import com.micartel.telemetry.domain.vehicle.VehicleAlreadyExistsException
import com.micartel.telemetry.domain.vehicle.VehicleRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*

internal class RegisterVehicleTest {
    private val mockVehicleRepository: VehicleRepository = mock(VehicleRepository::class.java)
    private val mockUniqueValidator: UniqueVehicleValidator = mock(UniqueVehicleValidator::class.java)
    private val handler: RegisterVehicle = RegisterVehicle(mockVehicleRepository, mockUniqueValidator)

    @Test
    fun `should register Vehicle's`() {
        val vehicle = VehicleBuilder().build()
        val command = VehicleRequest(
            vehicle.chassis.toString(),
            vehicle.licensePlate.toString(),
            vehicle.brand.toString(),
            vehicle.category.toString(),
            vehicle.inFleetDate.toString(),
        )
        doNothing().`when`(mockUniqueValidator).validate(vehicle)
        doNothing().`when`(mockVehicleRepository).save(vehicle)

        handler.execute(command)

        verify(mockUniqueValidator).validate(vehicle)
        verify(mockVehicleRepository).save(vehicle)
    }

    @Test
    fun `should not allow to register duplicated Vehicle's`() {
        val vehicle = VehicleBuilder().build()
        val command = VehicleRequest(
            vehicle.chassis.toString(),
            vehicle.licensePlate.toString(),
            vehicle.brand.toString(),
            vehicle.category.toString(),
            vehicle.inFleetDate.toString(),
        )

        doThrow(VehicleAlreadyExistsException::class.java)
            .`when`(mockUniqueValidator).validate(vehicle)

        assertThrows<VehicleAlreadyExistsException> {
            handler.execute(command)
        }
        verify(mockUniqueValidator).validate(vehicle)
    }
}