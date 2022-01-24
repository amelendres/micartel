package com.micartel.telemetry.domain.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class ChassisNumberTest : ValueObjectTest<ChassisNumber> {

    override fun createValue() = ChassisNumber("ChassisNumber1234")
    override fun createOtherValue() = ChassisNumber("ChassisNumber5678")

    @ParameterizedTest
    @ValueSource(strings = [ "tooShort", "[@invalid-chars!]", "ThisIsTooLongNumber111"])
    fun `should not allow the creation of invalid ChassisNumber's`(chassis:String) {
        assertThrows<IllegalArgumentException> {
            ChassisNumber(chassis)
        }
    }
}