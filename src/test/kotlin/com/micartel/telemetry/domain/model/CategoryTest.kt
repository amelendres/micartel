package com.micartel.telemetry.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CategoryTest : ValueObjectTest<Category> {

    override fun createValue() = Category("CCMN")
    override fun createOtherValue() = Category("CCAA")

    @Test
    fun `should not allow the creation of invalid Category's`() {
        assertThrows<IllegalArgumentException> {
            Category("AAAA")
        }
    }
}