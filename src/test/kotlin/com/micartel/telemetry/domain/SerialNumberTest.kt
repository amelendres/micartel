package com.micartel.telemetry.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class SerialNumberTest : ValueObjectTest<SerialNumber> {

    override fun createValue() = SerialNumber("G-34567")
    override fun createOtherValue() = SerialNumber("A-12345")

    @Test
    fun `should not allow the creation of invalid SerialNumber's`() {
        assertThrows<IllegalArgumentException> {
            SerialNumber("invalid")
        }
    }
}