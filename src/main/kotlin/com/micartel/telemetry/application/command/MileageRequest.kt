package com.micartel.telemetry.application.command

data class MileageRequest(
    val serialNumber:String,
    val mileage:Int,
    val unit:String
)