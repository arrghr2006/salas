package com.test.meetroomBooking.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTO_MeetRoomBooking {
    @NotBlank(message = "El nombre del responsable no puede ser nulo")
    private String responsable;

    @NotBlank(message = "El nombre de la sala no puede ser nulo")
    private String sala;

    @NotBlank(message = "La fecha de inicio de la reservacion no puede ser nula")
    //@JsonFormat( shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private String fechaInicio;

    @NotBlank(message = "La fecha final de la reservacion no puede ser nula")
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private String fechaFin;
    
}
