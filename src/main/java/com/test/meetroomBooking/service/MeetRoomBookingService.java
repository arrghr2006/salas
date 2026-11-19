package com.test.meetroomBooking.service;

import com.test.meetroomBooking.exception.ErrorCode;
import com.test.meetroomBooking.exception.ErrorMessage;
import com.test.meetroomBooking.exception.RequestException;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBooking;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBookingQuery;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBookingRequest;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBookingResponse;
import com.test.meetroomBooking.model.persistence.MeetRoom;
import com.test.meetroomBooking.model.persistence.MeetRoomBooking;
import com.test.meetroomBooking.repository.MeetRoomBookingRepository;
import com.test.meetroomBooking.repository.MeetRoomRepository;
import com.test.meetroomBooking.util.MeetRoomBookingDTOUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MeetRoomBookingService implements MeetRoomBookingServiceInterface {

    @Value("${json.param.date.format}")
    private String jsonParamDateFormat;

    private final MeetRoomBookingRepository meetRoomBookingRepository;
    private final MeetRoomRepository meetRoomRepository;

    @Transactional(readOnly = true)
    public List<DTO_MeetRoomBooking> getAll(Pageable pageable){
        Page<MeetRoomBooking> listado = meetRoomBookingRepository.findAll(pageable);
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

    @Transactional(readOnly = true)
    public List<DTO_MeetRoomBooking> getAllByFilters(DTO_MeetRoomBookingQuery filters, Pageable pageable){
        SimpleDateFormat sdf = new SimpleDateFormat(jsonParamDateFormat);
        Page<MeetRoomBooking> listado = null;
        List<DTO_MeetRoomBooking> resultadoList = new ArrayList<>();
        try{
            if(filters.getFecha()!=null) {
                Date fecha = sdf.parse(filters.getFecha());
                if(filters.getSala()!=null) {
                    listado = meetRoomBookingRepository.findMeetRoomBookingByFechaNombreSala(fecha, filters.getSala(), pageable);
                }
                else {
                    listado = meetRoomBookingRepository.findMeetRoomBookingByFecha(fecha, pageable);
                }
            }
            else if(filters.getSala()!=null) {
                listado = meetRoomBookingRepository.findMeetRoomBookingByNombreSala( filters.getSala(), pageable);
            }
            else{
                listado = meetRoomBookingRepository.findAll( pageable);
            }

            if(!listado.isEmpty()){
                listado.forEach(item -> {
                            resultadoList.add( MeetRoomBookingDTOUtil.toDTO(item));
                        }
                );
            }
            return resultadoList;
        } catch (ParseException e) {
            throw new RequestException( ErrorCode.BAD_REQUEST, ErrorMessage.NO_VALID_DATA,
                    ErrorMessage.NO_VALID_DATA, HttpStatus.BAD_REQUEST.name());
        }
    }

    public DTO_MeetRoomBooking getById(Integer id) throws RequestException {
        Optional<MeetRoomBooking> inventory = meetRoomBookingRepository.findById(id);
        if(inventory.isEmpty()) {
            throw new RequestException( ErrorCode.MEETROOM_NOT_FOUND, ErrorMessage.MEETROOM_NOT_FOUND,
                                        ErrorMessage.MEETROOM_NOT_FOUND, HttpStatus.NOT_FOUND.name());
        }
        return inventory.map(MeetRoomBookingDTOUtil::toDTO).get();
    }

    public DTO_MeetRoomBookingResponse create(DTO_MeetRoomBooking data){
        DTO_MeetRoomBookingRequest dto = null;
        try {
            dto = MeetRoomBookingDTOUtil.toRequestDTO(data);
        } catch (ParseException e) {
            throw new RequestException( ErrorCode.BAD_REQUEST, ErrorMessage.VALIDATION_PARAMS,
                    ErrorMessage.VALIDATION_PARAMS, HttpStatus.BAD_REQUEST.name());
        }

        //Encontrar si la sala estaba ocupada para el periodo de la reserva
        List<MeetRoomBooking> list = meetRoomBookingRepository.findMeetRoomBookingByPeriod( dto.getFechaInicio(), dto.getFechaFin(), dto.getSala());

        Optional<MeetRoom> sala = meetRoomRepository.findMeetRoomByNombre(dto.getSala());

        // Validar que exista la sala
        if(sala.get()==null){
            throw new RequestException( ErrorCode.MEETROOM_NOT_FOUND, ErrorMessage.MEETROOM_NOT_FOUND,
                    ErrorMessage.MEETROOM_NOT_FOUND, HttpStatus.NOT_FOUND.name());
        }

        //Validar que no este reservado en el periodo
        if(list!=null && !list.isEmpty()){
            throw new RequestException( ErrorCode.MEETROOM_RESERVED_IN_PERIOD, ErrorMessage.MEETROOM_RESERVED_IN_PERIOD,
                    ErrorMessage.MEETROOM_RESERVED_IN_PERIOD, HttpStatus.CONFLICT.name());
        }

        try {
            return MeetRoomBookingDTOUtil.toResponseDTO( meetRoomBookingRepository.save(MeetRoomBookingDTOUtil.toEntity(dto, sala.get())) );
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
