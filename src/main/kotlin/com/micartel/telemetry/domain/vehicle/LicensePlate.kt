package com.micartel.telemetry.domain.vehicle

@JvmInline
value class LicensePlate(private val value:String) {

    companion object{
        private val licensePlatePattern = "[A-Z0-9]+".toRegex()
    }
    
    init {
        require(licensePlatePattern.matches(value)) {
            "The licensePlate $value does not match the pattern $licensePlatePattern."
        }
    }

    override fun toString(): String {
        return value
    }
}