package com.micartel.telemetry.domain.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME

data class InFleetDate(val value: LocalDateTime) {
    init {
        val now = LocalDateTime.now()
        require(value.isBefore(now)) {
            "The InFleetDate $value should not be greater than now $now"
        }
    }

    companion object {
        fun fromString(str: String) = InFleetDate(LocalDateTime.parse(str, ISO_LOCAL_DATE_TIME))
    }

    override fun toString(): String {
        return value.format(ISO_LOCAL_DATE_TIME)
    }
}