package com.micartel.telemetry.port.adapter.ui.rest

import com.micartel.telemetry.application.command.RegisterVehicleCommand
import com.micartel.telemetry.application.command.RegisterVehicleHandler
import com.micartel.telemetry.domain.model.vehicle.VehicleAlreadyExistsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/vehicles")
class RegisterVehicleController(
    private val registerVehicleHandler: RegisterVehicleHandler
) {

    @PostMapping
    fun register(@RequestBody body: VehicleView): ResponseEntity<Void> {
        registerVehicleHandler.handle(
            RegisterVehicleCommand(
                body.chassis_number,
                body.license_plate,
                body.brand,
                body.category,
                body.infleet_date
            )
        )

        return ResponseEntity(HttpStatus.CREATED)
    }

    data class VehicleView(
        val chassis_number:String,
        val license_plate:String,
        val brand:String,
        val category:String,
        val infleet_date:String,
    ) {
    }

    @ExceptionHandler(VehicleAlreadyExistsException::class)
    fun handleVehicleAlreadyExistsException(response: HttpServletResponse, exception: VehicleAlreadyExistsException) {
        response.sendError(HttpServletResponse.SC_CONFLICT, exception.message)
    }
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(response: HttpServletResponse, exception: IllegalArgumentException) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.message)
    }
}