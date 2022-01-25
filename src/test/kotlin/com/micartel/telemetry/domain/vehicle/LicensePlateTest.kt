package com.micartel.telemetry.domain.vehicle

import com.micartel.telemetry.domain.ValueObjectTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class LicensePlateTest : ValueObjectTest<LicensePlate> {
    override fun createValue() = LicensePlate("1234ABC")
    override fun createOtherValue() = LicensePlate("5678CDF")

    @Test
    fun `should not allow the creation of invalid LicensePlate's`() {
        assertThrows<IllegalArgumentException> {
            LicensePlate("--")
        }
    }
}