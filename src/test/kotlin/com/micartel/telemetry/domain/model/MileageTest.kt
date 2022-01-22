package com.micartel.telemetry.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MileageTest : ValueObjectTest<Mileage> {

    override fun createValue() = Mileage(10, MileageUnit.KM)
    override fun createOtherValue() = Mileage(20, MileageUnit.KM)

    @Test
    fun `should not allow the creation of invalid Mileage's`() {
        assertThrows<IllegalArgumentException> {
            Mileage(-1, MileageUnit.KM)
        }
    }

    @Test
    fun `should greater than`() {
        val mileage = Mileage(10, MileageUnit.KM)
        val other = Mileage(9, MileageUnit.KM)

        assertThat(mileage.greaterThan(other)).isTrue
    }

    @Test
    fun `should not greater than`() {
        val mileage = Mileage(10, MileageUnit.KM)
        val other = Mileage(10, MileageUnit.KM)

        assertThat(mileage.greaterThan(other)).isFalse
    }

    @Test
    fun `should not equals`() {
        val mileage = Mileage(10, MileageUnit.KM)
        val other = Mileage(10, MileageUnit.MILE)

        assertThat(mileage).isNotEqualTo(other)

    }
}