package com.micartel.telemetry.domain.model

val chassisNumberPattern = "[a-zA-Z0-9]{17}".toRegex()

@JvmInline
value class ChassisNumber(private val value:String) {
    init {
        require(chassisNumberPattern.matches(value)) {
            "The ChassisNumber $value does not match the pattern $chassisNumberPattern."
        }
    }

    override fun toString(): String {
        return value
    }
}