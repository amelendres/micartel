package com.micartel.telemetry.application.command

data class VehicleRequest(
    val chassisNumber:String,
    val licensePlate:String,
    val brand:String,
    val category:String,
    val inFleetDate:String,
)