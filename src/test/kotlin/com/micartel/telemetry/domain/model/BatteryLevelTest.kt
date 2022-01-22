package com.micartel.telemetry.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BatteryLevelTest : ValueObjectTest<BatteryLevel> {

    override fun createValue() = BatteryLevel(100)
    override fun createOtherValue() = BatteryLevel(50)

    @Test
    fun `should not allow the creation of invalid BatteryLevel's`() {
        assertThrows<IllegalArgumentException> {
            BatteryLevel(110)
        }
    }
}