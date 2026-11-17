package com.test.meetroomBooking.service;

import com.test.meetroomBooking.exception.RequestException;
import com.test.meetroomBooking.model.dto.DTO_MeetRoom;

import java.util.List;

public interface MeetRoomServiceInterface {

    public List<DTO_MeetRoom> getAll();

    public DTO_MeetRoom getById(Integer id) throws RequestException;

    public DTO_MeetRoom getByName(String name) throws RequestException;
}
