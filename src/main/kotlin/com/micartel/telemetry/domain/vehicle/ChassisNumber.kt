package com.micartel.telemetry.domain.vehicle

@JvmInline
value class ChassisNumber(private val value:String) {
    companion object{
        private val chassisNumberPattern = "[a-zA-Z0-9]{17}".toRegex()
    }

    init {
        require(chassisNumberPattern.matches(value)) {
            "The ChassisNumber $value does not match the pattern $chassisNumberPattern."
        }
    }

    override fun toString(): String {
        return value
    }
}