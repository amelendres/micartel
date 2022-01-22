package com.micartel.telemetry.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class FuelLevelTest : ValueObjectTest<FuelLevel> {

    override fun createValue() = FuelLevel(10)
    override fun createOtherValue() = FuelLevel(20)

    @Test
    fun `should not allow the creation of invalid FuelLevel's`() {
        assertThrows<IllegalArgumentException> {
            FuelLevel(-1)
        }
    }

    @Test
    fun `should increment`() {
        val level = FuelLevel(50)

        val incremented = level.increment(FuelLevel(20))

        assertThat(incremented.value).isEqualTo(70)
    }

    @Test
    fun `should decrement`() {
        val level = FuelLevel(100)

        val decremented = level.decrement(FuelLevel(5))

        assertThat(decremented.value).isEqualTo(95)
    }
}