package com.test.meetroomBooking.controller;

import com.test.meetroomBooking.exception.RequestException;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBooking;
import com.test.meetroomBooking.model.dto.MeetRoomBookingResponseDTO;
import com.test.meetroomBooking.payload.MessageResponse;
import com.test.meetroomBooking.service.MeetRoomBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reservas")
public class MeetRoomBookingController {

    @Autowired
    private MeetRoomBookingService meetRoomBookingService;

    @GetMapping
    public ResponseEntity<MessageResponse> getAll(){
        List<DTO_MeetRoomBooking> listado = meetRoomBookingService.getAll();
        return listado!=null && !listado.isEmpty()
                ? new ResponseEntity<>( MessageResponse.builder().status(HttpStatus.OK).object(listado).message("Listado de salas").build(),
                HttpStatus.OK)
                : new ResponseEntity<>( MessageResponse.builder().status(HttpStatus.NOT_FOUND).object(listado).message("Listado de salas").build(),
                HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getById(@PathVariable Integer id) throws RequestException {
        DTO_MeetRoomBooking dto = meetRoomBookingService.getById(id);
        return dto!=null
                ? new ResponseEntity<>( MessageResponse.builder().status(HttpStatus.OK).object(dto).message(null).build(),
                HttpStatus.OK)
                : new ResponseEntity<>( MessageResponse.builder().status(HttpStatus.NOT_FOUND).object(dto).message("Elemento no encontrado").build(),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> create(@RequestBody @Valid DTO_MeetRoomBooking dto) throws RequestException {
        MeetRoomBookingResponseDTO resultado = meetRoomBookingService.create(dto);
        return new ResponseEntity<>( MessageResponse.builder().status(HttpStatus.OK).object(resultado).message(null).build(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> eliminar(@PathVariable Integer id) {
        meetRoomBookingService.cancel(id);
        return new ResponseEntity<>( MessageResponse.builder().status(HttpStatus.OK).object(null).message("Se eliminó la reservacion correctamente").build(), HttpStatus.OK);
    }

}
