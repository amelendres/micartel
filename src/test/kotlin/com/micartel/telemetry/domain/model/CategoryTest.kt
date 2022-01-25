package com.micartel.telemetry.domain.model

import com.micartel.telemetry.domain.model.vehicle.Category
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class CategoryTest : ValueObjectTest<Category> {

    override fun createValue() = Category("CCMN")
    override fun createOtherValue() = Category("CCAA")

    @ParameterizedTest
    @ValueSource(strings = [ "FEW", "AAAA", "1234", "ABCDEFGH", "", "----", "ccmn"])
    fun `should not allow the creation of invalid Category's`(cat:String) {
        assertThrows<IllegalArgumentException> {
            Category(cat)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = [ "CCAE", "CCAC"])
    fun `should validate electric category`(cat:String) {
        val category = Category(cat)
        assertThat(category.isElectric()).isTrue
    }

    @ParameterizedTest
    @ValueSource(strings = [ "CCAH", "CCAI"])
    fun `should validate hybrid category`(cat:String) {
        val category = Category(cat)
        assertThat(category.isHybrid()).isTrue
    }
}