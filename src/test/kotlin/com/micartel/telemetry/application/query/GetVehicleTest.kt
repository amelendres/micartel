package com.micartel.telemetry.application.query

import com.micartel.telemetry.domain.builder.VehicleBuilder
import com.micartel.telemetry.domain.vehicle.ChassisNumber
import com.micartel.telemetry.domain.vehicle.VehicleNotFoundException
import com.micartel.telemetry.domain.vehicle.VehicleRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*

internal class GetVehicleTest {
    private val mockVehicleRepository: VehicleRepository = mock(VehicleRepository::class.java)
    private val query: GetVehicle = GetVehicle(mockVehicleRepository)

    @Test
    fun `should get a Vehicle`() {
        val vehicle = VehicleBuilder().build()

        doReturn(vehicle).`when`(mockVehicleRepository).find(vehicle.chassis)

        query.execute(vehicle.chassis.toString())

        verify(mockVehicleRepository).find(vehicle.chassis)
    }

    @Test
    fun `should fail to get a non existing vehicle`() {
        val chassis = ChassisNumber("ChassisNumber1234");
        doReturn(null).`when`(mockVehicleRepository).find(chassis)

        assertThrows<VehicleNotFoundException> {
            query.execute(chassis.toString())
        }
        verify(mockVehicleRepository).find(chassis)
    }
}