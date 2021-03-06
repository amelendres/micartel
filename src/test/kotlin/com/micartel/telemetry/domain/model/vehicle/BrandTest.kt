package com.micartel.telemetry.domain.model.vehicle

import com.micartel.telemetry.domain.model.ValueObjectTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BrandTest : ValueObjectTest<Brand> {

    override fun createValue() = Brand("Toyota")
    override fun createOtherValue() = Brand("Volvo")

    @Test
    fun `should not allow the creation of invalid Brand's`() {
        assertThrows<IllegalArgumentException> {
            Brand("")
        }
    }
}