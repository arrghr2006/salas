package com.test.meetroomBooking.service;

import com.test.meetroomBooking.exception.RequestException;
import com.test.meetroomBooking.model.dto.DTO_MeetRoom;

import java.util.List;

public interface MeetRoomServiceInterface {

    List<DTO_MeetRoom> getAll();

    DTO_MeetRoom getById(Integer id) throws RequestException;

    DTO_MeetRoom getByName(String name) throws RequestException;
}
