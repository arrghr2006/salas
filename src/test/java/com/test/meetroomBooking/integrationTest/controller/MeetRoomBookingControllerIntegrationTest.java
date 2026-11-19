package com.test.meetroomBooking.integrationTest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBooking;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBookingQuery;
import com.test.meetroomBooking.model.persistence.MeetRoom;
import com.test.meetroomBooking.model.persistence.MeetRoomBooking;
import com.test.meetroomBooking.model.persistence.UserApp;
import com.test.meetroomBooking.repository.MeetRoomBookingRepository;
import com.test.meetroomBooking.repository.MeetRoomRepository;
import com.test.meetroomBooking.repository.UserAppRepository;
import com.test.meetroomBooking.security.jwt.JwtUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:controller_test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.jpa.properties.hibernate.hbm2ddl.import_files=none"
})
@AutoConfigureMockMvc(addFilters = true)
class MeetRoomBookingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MeetRoomRepository meetRoomRepository;

    @Autowired
    private MeetRoomBookingRepository meetRoomBookingRepository;

    @Autowired
    private UserAppRepository userAppRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static String validToken;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @BeforeAll
    static void beforeAll(@Autowired JwtUtils jwtUtils,
                          @Autowired MeetRoomRepository meetRoomRepository,
                          @Autowired MeetRoomBookingRepository meetRoomBookingRepository,
                          @Autowired UserAppRepository userAppRepository,
                          @Autowired PasswordEncoder passwordEncoder) throws Exception {
        validToken = jwtUtils.generateToken("admin");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // Crear salas
        MeetRoom sala1 = new MeetRoom();
        sala1.setNombre("sala-1");
        meetRoomRepository.save(sala1);

        MeetRoom sala2 = new MeetRoom();
        sala2.setNombre("sala-2");
        meetRoomRepository.save(sala2);

        MeetRoom sala3 = new MeetRoom();
        sala3.setNombre("sala-3");
        meetRoomRepository.save(sala3);

        // Crear usuario admin
        UserApp admin = new UserApp();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        userAppRepository.save(admin);

        // Crear algunas reservas de ejemplo
        Date fechaInicio1 = sdf.parse("01/11/2026 08:00:00");
        Date fechaFin1 = sdf.parse("02/11/2026 16:00:00");
        meetRoomBookingRepository.save(new MeetRoomBooking(sala1, "victor", fechaInicio1, fechaFin1));

        Date fechaInicio2 = sdf.parse("03/11/2026 08:00:00");
        Date fechaFin2 = sdf.parse("04/11/2026 16:00:00");
        meetRoomBookingRepository.save(new MeetRoomBooking(sala1, "victor", fechaInicio2, fechaFin2));

        Date fechaInicio3 = sdf.parse("01/11/2026 08:00:00");
        Date fechaFin3 = sdf.parse("02/11/2026 16:00:00");
        meetRoomBookingRepository.save(new MeetRoomBooking(sala2, "victor", fechaInicio3, fechaFin3));
    }

     // ==================== POST /reservas/list (público, Order 1) ====================

    @Test
    @DisplayName("POST /reservas/list - debería devolver reservas filtradas por sala")
    void should_return_filtered_bookings_by_sala() throws Exception {
        DTO_MeetRoomBookingQuery query = new DTO_MeetRoomBookingQuery();
        query.setSala("sala-1");

        mockMvc.perform(post("/reservas/list")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(query))
                    .param("page", "0")
                    .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /reservas/list - debería devolver reservas filtradas por fecha")
    void should_return_filtered_bookings_by_fecha() throws Exception {
        DTO_MeetRoomBookingQuery query = new DTO_MeetRoomBookingQuery();
        query.setFecha("01/11/2026 08:00:00");

        mockMvc.perform(post("/reservas/list")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(query)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /reservas/list - debería devolver reservas filtradas por fecha y sala")
    void should_return_filtered_bookings_by_fecha_and_sala() throws Exception {
        DTO_MeetRoomBookingQuery query = new DTO_MeetRoomBookingQuery();
        query.setFecha("01/11/2026 08:00:00");
        query.setSala("sala-2");

        mockMvc.perform(post("/reservas/list")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(query)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /reservas/list - debería fallar por formato de fecha incorrecto")
    void should_return_error_filtering_bookings_by_fecha_bad_format() throws Exception {
        DTO_MeetRoomBookingQuery query = new DTO_MeetRoomBookingQuery();
        query.setFecha("2026/11/01 08:00:00");

        mockMvc.perform(post("/reservas/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(query)))
                .andExpect(status().isNotFound());
    }

    // ==================== GET /reservas/{id} (requiere autenticación, Order 2) ====================

    @Test
    @DisplayName("GET /reservas/{id} - debería devolver una reserva por ID con autenticación")
    void should_return_booking_by_id() throws Exception {
        mockMvc.perform(get("/reservas/2")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + validToken))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /reservas/{id} - debería devolver 404 para ID inexistente con autenticación")
    void should_return_404_for_nonexistent_booking() throws Exception {
        mockMvc.perform(get("/reservas/9999")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + validToken))
                .andExpect(status().isNotFound());
    }

    // ==================== POST /reservas/add (requiere autenticación, Order 1) ====================

    @Test
    @DisplayName("POST /reservas/add - debería requerir autenticación sin token")
    void should_require_auth_for_add_without_token() throws Exception {
        DTO_MeetRoomBooking dto = new DTO_MeetRoomBooking();
        dto.setResponsable("juan");
        dto.setSala("sala-1");
        dto.setFechaInicio("20/06/2027 08:00:00");
        dto.setFechaFin("20/06/2027 10:00:00");

        mockMvc.perform(post("/reservas/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("POST /reservas/add - debería crear reserva con token válido")
    void should_create_booking_with_valid_token() throws Exception {
        DTO_MeetRoomBooking dto = new DTO_MeetRoomBooking();
        dto.setResponsable("juan");
        dto.setSala("sala-1");
        dto.setFechaInicio("25/06/2027 08:00:00");
        dto.setFechaFin("25/06/2027 10:00:00");

        mockMvc.perform(post("/reservas/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto))
                    .header("Authorization", "Bearer " + validToken))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /reservas/add - debería rechazar reserva con token inválido")
    void should_reject_booking_with_invalid_token() throws Exception {
        DTO_MeetRoomBooking dto = new DTO_MeetRoomBooking();
        dto.setResponsable("juan");
        dto.setSala("sala-1");
        dto.setFechaInicio("28/06/2027 08:00:00");
        dto.setFechaFin("28/06/2027 10:00:00");

        mockMvc.perform(post("/reservas/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto))
                    .header("Authorization", "Bearer tokenInvalido"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("POST /reservas/add - debería rechazar reserva con campos vacíos")
    void should_reject_booking_with_empty_fields() throws Exception {
        DTO_MeetRoomBooking dto = new DTO_MeetRoomBooking();
        dto.setResponsable("");
        dto.setSala("");
        dto.setFechaInicio("");
        dto.setFechaFin("");

        mockMvc.perform(post("/reservas/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto))
                    .header("Authorization", "Bearer " + validToken))
                .andExpect(status().isBadRequest());
    }

    // ==================== DELETE /reservas/{id} (requiere autenticación, Order 2) ====================

    @Test
    @DisplayName("DELETE /reservas/{id} - debería eliminar una reserva existente con autenticación")
    void should_delete_existing_booking() throws Exception {
        mockMvc.perform(delete("/reservas/3")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + validToken))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /reservas/{id} - debería devolver 404 para ID inexistente con autenticación")
    void should_return_404_for_delete_nonexistent_booking() throws Exception {
        mockMvc.perform(delete("/reservas/9999")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + validToken))
                .andExpect(status().isNotFound());
    }
}
