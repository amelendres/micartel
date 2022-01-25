package com.micartel.telemetry.domain.vehicle

@JvmInline
value class Brand(private val value: String) {
    init {
        require(value.isNotEmpty()) {
            "The Brand $value should not be empty"
        }
    }

    override fun toString(): String {
        return value
    }
}