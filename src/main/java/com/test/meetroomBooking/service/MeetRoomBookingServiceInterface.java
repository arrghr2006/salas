package com.test.meetroomBooking.service;

import com.test.meetroomBooking.exception.RequestException;
import com.test.meetroomBooking.model.dto.DTO_MeetRoom;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBooking;
import com.test.meetroomBooking.model.dto.MeetRoomBookingResponseDTO;
import com.test.meetroomBooking.model.persistence.MeetRoomBooking;

import java.util.Date;
import java.util.List;

public interface MeetRoomBookingServiceInterface {

    List<DTO_MeetRoomBooking> getAll();

    DTO_MeetRoomBooking getById(Integer id) throws RequestException;

    DTO_MeetRoomBooking getByFecha(Date fecha) throws RequestException;

    MeetRoomBookingResponseDTO create(DTO_MeetRoomBooking dto);

    void cancel(Integer id);
}
