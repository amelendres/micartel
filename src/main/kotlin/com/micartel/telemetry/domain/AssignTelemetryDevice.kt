package com.micartel.telemetry.domain

import com.micartel.telemetry.domain.vehicle.Vehicle

fun Vehicle.assignTelemetryDevice(device: SerialNumber) = TelemetricVehicle(this.chassis, this.category, device)