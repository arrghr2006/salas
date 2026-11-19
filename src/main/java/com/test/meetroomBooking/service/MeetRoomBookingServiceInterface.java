package com.test.meetroomBooking.service;

import com.test.meetroomBooking.exception.RequestException;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBooking;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBookingResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MeetRoomBookingServiceInterface {

    List<DTO_MeetRoomBooking> getAll(Pageable pageable);

    DTO_MeetRoomBooking getById(Integer id) throws RequestException;

    //DTO_MeetRoomBooking getByFecha(Date fecha) throws RequestException;

    DTO_MeetRoomBookingResponse create(DTO_MeetRoomBooking dto);

    void cancel(Integer id);
}
