package com.micartel.telemetry.domain.vehicle

import com.micartel.telemetry.domain.ValueObjectTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME

internal class InFleetDateTest : ValueObjectTest<InFleetDate> {
    val now: LocalDateTime = LocalDateTime.now()
    override fun createValue() = InFleetDate(now)
    override fun createOtherValue() = InFleetDate(LocalDateTime.now().minusSeconds(10))

    @Test
    fun `should not allow the creation of invalid InFleetDate's`() {
        assertThrows<IllegalArgumentException> {
            InFleetDate(LocalDateTime.now().plusSeconds(1))
        }
    }

    @Test
    fun `should format to ISO_LOCAL_DATE_TIME InFleetDate's`() {
        assertThat(InFleetDate(now).toString())
            .isEqualTo(now.format(ISO_LOCAL_DATE_TIME))
    }

    @Test
    fun `should create from string`() {
        val date = "2000-10-31T01:30:00"
        assertThat(InFleetDate.fromString(date).toString()).isEqualTo(date)
    }
}
