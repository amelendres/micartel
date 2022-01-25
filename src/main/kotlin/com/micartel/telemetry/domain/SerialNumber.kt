package com.micartel.telemetry.domain

data class SerialNumber(val value: String) {

    companion object{
        private val serialNumberPattern = "^[A-Z]-\\d{5}".toRegex()
    }
    init {
        require(serialNumberPattern.matches(value)) {
            "The SerialNumber $value does not match the pattern $serialNumberPattern."
        }
    }

    override fun toString(): String {
        return value
    }
}