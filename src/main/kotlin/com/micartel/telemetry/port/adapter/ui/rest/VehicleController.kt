package com.micartel.telemetry.port.adapter.ui.rest

import com.micartel.telemetry.application.command.RegisterVehicle
import com.micartel.telemetry.application.command.VehicleRequest
import com.micartel.telemetry.application.query.GetVehicle
import com.micartel.telemetry.domain.vehicle.Vehicle
import com.micartel.telemetry.domain.vehicle.VehicleAlreadyExistsException
import com.micartel.telemetry.domain.vehicle.VehicleNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/vehicles")
class VehicleController(
    private val registerVehicle: RegisterVehicle,
    private val getVehicle: GetVehicle
) {

    @GetMapping("/{id}")
    fun showDetails(@PathVariable id:String): ResponseEntity<Vehicle> {
        val vehicle = getVehicle.execute(id)
        return ResponseEntity.ok(vehicle)
    }

    @PostMapping
    fun register(@RequestBody req: VehicleRequest): ResponseEntity<Void> {
        registerVehicle.execute(req)
        val locationOfNewVehicle = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(req.chassisNumber)
                .toUri();
        return ResponseEntity.created(locationOfNewVehicle).build()
    }

    @ExceptionHandler(VehicleNotFoundException::class)
    fun handleNotFoundException(response: HttpServletResponse, exception: VehicleNotFoundException) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, exception.message)
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