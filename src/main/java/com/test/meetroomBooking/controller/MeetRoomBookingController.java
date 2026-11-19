package com.test.meetroomBooking.controller;

import com.test.meetroomBooking.exception.RequestException;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBooking;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBookingQuery;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBookingResponse;
import com.test.meetroomBooking.payload.MessageResponse;
import com.test.meetroomBooking.service.MeetRoomBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<MessageResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "fechaInicio") String sort,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ){
        //Se construye el pageable, que tiene todos los detalles de cómo debe hacerse la paginación.
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));

        List<DTO_MeetRoomBooking> listado = meetRoomBookingService.getAll(pageable);
        return listado!=null && !listado.isEmpty()
                ? new ResponseEntity<>( MessageResponse.builder().status(HttpStatus.OK).object(listado).message("Listado de reservas").build(),
                HttpStatus.OK)
                : new ResponseEntity<>( MessageResponse.builder().status(HttpStatus.NOT_FOUND).object(listado).message("Listado de reservas").build(),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping("/list")
    public ResponseEntity<MessageResponse> getList(@RequestBody @Valid DTO_MeetRoomBookingQuery dto,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "fechaInicio") String sort,
                                                   @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ){
        //Se construye el pageable, que tiene todos los detalles de cómo debe hacerse la paginación.
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));

        List<DTO_MeetRoomBooking> listado = meetRoomBookingService.getAllByFilters(dto, pageable);
        return listado!=null && !listado.isEmpty()
                ? new ResponseEntity<>( MessageResponse.builder().status(HttpStatus.OK).object(listado).message("Listado de reservas").build(),
                HttpStatus.OK)
                : new ResponseEntity<>( MessageResponse.builder().status(HttpStatus.NOT_FOUND).object(listado).message("Listado de reservas").build(),
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
        DTO_MeetRoomBookingResponse resultado = meetRoomBookingService.create(dto);
        return new ResponseEntity<>( MessageResponse.builder().status(HttpStatus.OK).object(resultado).message(null).build(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> eliminar(@PathVariable Integer id) {
        meetRoomBookingService.cancel(id);
        return new ResponseEntity<>( MessageResponse.builder().status(HttpStatus.OK).object(null).message("Se eliminó la reservacion correctamente").build(), HttpStatus.OK);
    }

}
