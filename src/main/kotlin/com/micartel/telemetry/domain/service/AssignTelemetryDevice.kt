package com.micartel.telemetry.domain.service

import com.micartel.telemetry.domain.model.SerialNumber
import com.micartel.telemetry.domain.model.TelemetricVehicle
import com.micartel.telemetry.domain.model.Vehicle

fun Vehicle.assignTelemetryDevice(device: SerialNumber) = TelemetricVehicle(this.chassis, this.category, device)