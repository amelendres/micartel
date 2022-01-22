package com.micartel.telemetry.domain.model

val serialNumberPattern = "^[A-Z]-\\d{5}".toRegex()

data class SerialNumber(val value: String) {
    init {
        require(serialNumberPattern.matches(value)) {
            "The SerialNumber $value does not match the pattern $serialNumberPattern."
        }
    }

    override fun toString(): String {
        return value
    }
}