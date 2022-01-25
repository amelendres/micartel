package com.micartel.telemetry.port.adapter.ui.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.micartel.telemetry.application.command.RegisterVehicle
import com.micartel.telemetry.application.command.VehicleRequest
import com.micartel.telemetry.application.query.GetVehicle
import com.micartel.telemetry.domain.builder.VehicleBuilder
import com.micartel.telemetry.domain.vehicle.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime


@WebMvcTest(VehicleController::class)
class VehicleControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var getVehicle: GetVehicle

    @MockBean
    private lateinit var registerVehicle: RegisterVehicle

    @Nested
    inner class GetVehicleTest {
        @Test
        fun `Should return a Vehicle`() {
            val vehicle = VehicleBuilder().build();
            given(getVehicle.execute(anyString()))
                    .willReturn(vehicle)

            mvc.perform(get("/vehicles/ChassisNumber1234"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("chassis").value("ChassisNumber1234"))
                    .andExpect(jsonPath("brand").value("Toyota"))

            verify(getVehicle).execute(anyString())
        }

        @Test
        fun `Should fail to return a non existing vehicle`() {
            val ex = VehicleNotFoundException("ChassisNumber1234");
            given(getVehicle.execute(anyString()))
                    .willThrow(ex)

            mvc.perform(get("/vehicles/ChassisNumber1234"))
                    .andExpect(status().isNotFound())

            verify(getVehicle).execute(anyString())
        }


        @Test
        fun `Should fail to get a vehicle with invalid chassis`() {
            val ex = IllegalArgumentException("no-valid");
            given(getVehicle.execute(anyString()))
                    .willThrow(ex)

            mvc.perform(get("/vehicles/ChassisNumber1234"))
                    .andExpect(status().isBadRequest())

            verify(getVehicle).execute(anyString())
        }
    }


    @Nested
    inner class CreateVehicle {
        @Test
        fun `Should register a new Vehicle`() {
            val vehicleRequest = VehicleRequest(
                    "ChassisNumber1234",
                    "1234ABC",
                    "Toyota",
                    "CCMN",
                    LocalDateTime.now().toString()
            )

            mvc.perform(post("/vehicles")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(vehicleRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(header().string("Location", "http://localhost/vehicles/ChassisNumber1234"))
                    .andReturn().getResponse().getHeader("Location")
        }
        @Test
        fun `Should fail to register an existing vehicle`() {
            val vehicleRequest = VehicleRequest(
                    "ChassisNumber1234",
                    "1234ABC",
                    "Toyota",
                    "CCMN",
                    LocalDateTime.now().toString()
            )

            given(registerVehicle.execute(vehicleRequest))
                    .willThrow(VehicleAlreadyExistsException(ChassisNumber("ChassisNumber1234")))

            mvc.perform(post("/vehicles")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(vehicleRequest)))
                    .andExpect(status().isConflict())

            verify(registerVehicle).execute(vehicleRequest)
        }
    }

    protected fun asJsonString(obj: Any?): String {
        try {
            val mapper = ObjectMapper()
            val jsonContent = mapper.writeValueAsString(obj)
            return jsonContent
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}