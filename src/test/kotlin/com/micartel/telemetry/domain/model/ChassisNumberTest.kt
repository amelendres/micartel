package com.micartel.telemetry.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ChassisNumberTest : ValueObjectTest<ChassisNumber> {

    override fun createValue() = ChassisNumber("ChassisNumber1234")
    override fun createOtherValue() = ChassisNumber("ChassisNumber5678")

    @Test
    fun `should not allow the creation of invalid ChassisNumber's`() {
        assertThrows<IllegalArgumentException> {
            ChassisNumber("tooShort")
        }
    }
}