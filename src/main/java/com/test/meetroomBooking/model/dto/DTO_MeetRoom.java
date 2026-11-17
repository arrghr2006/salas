package com.test.meetroomBooking.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTO_MeetRoom {
    @NotBlank(message = "El nombre de la sala no puede ser nulo")
    private String nombre;

}
