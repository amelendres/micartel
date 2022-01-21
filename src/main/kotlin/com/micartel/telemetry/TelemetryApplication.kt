package com.micartel.telemetry

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TelemetryApplication

fun main(args: Array<String>) {
    runApplication<TelemetryApplication>(*args)
}
