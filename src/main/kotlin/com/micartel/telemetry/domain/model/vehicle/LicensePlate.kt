package com.micartel.telemetry.domain.model.vehicle

val licensePlatePattern = "[A-Z0-9]+".toRegex()

@JvmInline
value class LicensePlate(private val value:String) {
    init {
        require(licensePlatePattern.matches(value)) {
            "The licensePlate $value does not match the pattern $licensePlatePattern."
        }
    }

    override fun toString(): String {
        return value
    }
}