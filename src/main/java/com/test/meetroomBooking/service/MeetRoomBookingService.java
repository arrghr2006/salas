package com.test.meetroomBooking.service;

import com.test.meetroomBooking.exception.ErrorCode;
import com.test.meetroomBooking.exception.ErrorMessage;
import com.test.meetroomBooking.exception.RequestException;
import com.test.meetroomBooking.model.dto.DTO_MeetRoom;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBooking;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBookingRequest;
import com.test.meetroomBooking.model.dto.MeetRoomBookingResponseDTO;
import com.test.meetroomBooking.model.persistence.MeetRoom;
import com.test.meetroomBooking.model.persistence.MeetRoomBooking;
import com.test.meetroomBooking.repository.MeetRoomBookingRepository;
import com.test.meetroomBooking.repository.MeetRoomRepository;
import com.test.meetroomBooking.util.MeetRoomBookingDTOUtil;
import com.test.meetroomBooking.util.MeetRoomDTOUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MeetRoomBookingService implements MeetRoomBookingServiceInterface {

    private final MeetRoomBookingRepository meetRoomBookingRepository;
    private final MeetRoomRepository meetRoomRepository;

    public List<DTO_MeetRoomBooking> getAll(){
        List<MeetRoomBooking> listado = meetRoomBookingRepository.findAll();
        List<DTO_MeetRoomBooking> resultadoList = new ArrayList<>();

        if(!listado.isEmpty()){
            listado.forEach(
                    item -> {
                        resultadoList.add( MeetRoomBookingDTOUtil.toDTO(item));
                    }
            );
        }
        return resultadoList;
    }

    public DTO_MeetRoomBooking getById(Integer id) throws RequestException {
        Optional<MeetRoomBooking> inventory = meetRoomBookingRepository.findById(id);
        if(inventory.isEmpty()) {
            throw new RequestException( ErrorCode.MEETROOM_NOT_FOUND, ErrorMessage.MEETROOM_NOT_FOUND,
                                        ErrorMessage.MEETROOM_NOT_FOUND, HttpStatus.NOT_FOUND.name());
        }
        return inventory.map(MeetRoomBookingDTOUtil::toDTO).get();
    }

    public DTO_MeetRoomBooking getByFecha(Date fecha) throws RequestException {
        Optional<MeetRoomBooking> inventory = meetRoomBookingRepository.findMeetRoomBookingByFecha(fecha);
        if(inventory.isEmpty()) {
            throw new RequestException( ErrorCode.MEETROOM_NOT_FOUND, ErrorMessage.MEETROOM_NOT_FOUND,
                    ErrorMessage.MEETROOM_NOT_FOUND, HttpStatus.NOT_FOUND.name());
        }
        return inventory.map(MeetRoomBookingDTOUtil::toDTO).get();
    }

    public MeetRoomBookingResponseDTO create(DTO_MeetRoomBooking data){
        DTO_MeetRoomBookingRequest dto = null;
        try {
            dto = MeetRoomBookingDTOUtil.toRequestDTO(data);
        } catch (ParseException e) {
            throw new RequestException( ErrorCode.BAD_REQUEST, ErrorMessage.VALIDATION_PARAMS,
                    ErrorMessage.VALIDATION_PARAMS, HttpStatus.BAD_REQUEST.name());
        }

        List<MeetRoomBooking> list = meetRoomBookingRepository.findMeetRoomBookingByPeriod( dto.getFechaInicio(), dto.getFechaFin());

        Optional<MeetRoom> sala = meetRoomRepository.findMeetRoomByName(dto.getSala());

        if(sala.get()==null){
            throw new RequestException( ErrorCode.MEETROOM_NOT_FOUND, ErrorMessage.MEETROOM_NOT_FOUND,
                    ErrorMessage.MEETROOM_NOT_FOUND, HttpStatus.NOT_FOUND.name());
        }

        if(list!=null && !list.isEmpty()){
            throw new RequestException( ErrorCode.MEETROOM_RESERVED_IN_PERIOD, ErrorMessage.MEETROOM_RESERVED_IN_PERIOD,
                    ErrorMessage.MEETROOM_RESERVED_IN_PERIOD, HttpStatus.CONFLICT.name());
        }

        try {
            MeetRoomBooking entity = meetRoomBookingRepository.save(MeetRoomBookingDTOUtil.toEntity(dto, sala.get()));
            return MeetRoomBookingDTOUtil.toResponseDTO( entity );
        }catch (IllegalArgumentException exc){
            throw new RequestException( ErrorCode.BAD_REQUEST, ErrorMessage.VALIDATION_PARAMS,
                    ErrorMessage.VALIDATION_PARAMS, HttpStatus.BAD_REQUEST.name());
        }

    }

    public void cancel(Integer id){
        Optional<MeetRoomBooking> entity = meetRoomBookingRepository.findById(id);
        if(entity.isEmpty()) {
            throw new RequestException( ErrorCode.MEETROOMBOOKING_NOT_FOUND, ErrorMessage.MEETROOMBOOKING_NOT_FOUND,
                    ErrorMessage.MEETROOMBOOKING_NOT_FOUND, HttpStatus.NOT_FOUND.name());
        }
        meetRoomBookingRepository.deleteById(id);
    }
}
