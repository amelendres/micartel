package com.micartel.telemetry.application.command

data class RegisterMileageCommand(
    val serialNumber:String,
    val mileage:Int,
    val unit:String
)