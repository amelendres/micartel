package com.micartel.telemetry.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MileageTest : ValueObjectTest<Mileage> {

    override fun createValue() = Mileage(10, MileageUnit.KM)
    override fun createOtherValue() = Mileage(20, MileageUnit.KM)


    @ParameterizedTest
    @CsvSource(
        "-100, Km",
        "100, gr",
        "100, m",
        "100, €",
    )
    fun `should not allow the creation of invalid Mileage's`(mileage:Int, unit:String) {
        assertThrows<IllegalArgumentException> {
            Mileage(mileage, MileageUnit.valueOf(unit))
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