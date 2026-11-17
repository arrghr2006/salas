package com.test.meetroomBooking.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetRoomBookingResponseDTO {

    private Integer id;

    private String responsable;

    private String sala;

    private Date fechaInicio;

    private Date fechaFin;

}
