package com.test.meetroomBooking.service;

import com.test.meetroomBooking.exception.ErrorCode;
import com.test.meetroomBooking.exception.ErrorMessage;
import com.test.meetroomBooking.exception.RequestException;
import com.test.meetroomBooking.model.dto.DTO_MeetRoom;
import com.test.meetroomBooking.model.persistence.MeetRoom;
import com.test.meetroomBooking.repository.MeetRoomRepository;
import com.test.meetroomBooking.util.MeetRoomDTOUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MeetRoomService implements MeetRoomServiceInterface {

    private final MeetRoomRepository meetRoomRepository;

    public List<DTO_MeetRoom> getAll(){
        List<MeetRoom> listado = meetRoomRepository.findAll();
        List<DTO_MeetRoom> resultadoList = new ArrayList<>();

        if(!listado.isEmpty()){
            listado.forEach(
                    item -> {
                        resultadoList.add( MeetRoomDTOUtil.toDTO(item));
                    }
            );
        }
        return resultadoList;
    }

    public DTO_MeetRoom getById(Integer id) throws RequestException {
        Optional<MeetRoom> inventory = meetRoomRepository.findById(id);
        if(inventory.isEmpty()) {
            throw new RequestException( ErrorCode.MEETROOM_NOT_FOUND, ErrorMessage.MEETROOM_NOT_FOUND,
                                        ErrorMessage.MEETROOM_NOT_FOUND, HttpStatus.NOT_FOUND.name());
        }
        return inventory.map(MeetRoomDTOUtil::toDTO).get();
    }

    public DTO_MeetRoom getByName(String name) throws RequestException {
        Optional<MeetRoom> inventory = meetRoomRepository.findMeetRoomByName(name);
        if(inventory.isEmpty()) {
            throw new RequestException( ErrorCode.MEETROOM_NOT_FOUND, ErrorMessage.MEETROOM_NOT_FOUND,
                    ErrorMessage.MEETROOM_NOT_FOUND, HttpStatus.NOT_FOUND.name());
        }
        return inventory.map(MeetRoomDTOUtil::toDTO).get();
    }

}
