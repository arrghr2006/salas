package com.test.meetroomBooking.util;

import com.test.meetroomBooking.model.dto.DTO_MeetRoom;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBooking;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBookingRequest;
import com.test.meetroomBooking.model.dto.MeetRoomBookingResponseDTO;
import com.test.meetroomBooking.model.persistence.MeetRoom;
import com.test.meetroomBooking.model.persistence.MeetRoomBooking;
import com.test.meetroomBooking.repository.MeetRoomRepository;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class MeetRoomBookingDTOUtil {

    public static String DATE_FORMAT_PATTERN = "dd/MM/yyyy HH:mm:ss";

     public static DTO_MeetRoomBooking toDTO(MeetRoomBooking entity){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        DTO_MeetRoomBooking dto = new DTO_MeetRoomBooking();
        BeanUtils.copyProperties(entity, dto);
        dto.setSala(entity.getMeetRoom().getNombre());
        dto.setFechaInicio( sdf.format(entity.getFechaInicio()) );
        dto.setFechaFin( sdf.format(entity.getFechaFin()) );
        return dto;
    }

    public static MeetRoomBooking toEntity(DTO_MeetRoomBookingRequest dto, MeetRoom room){
        return new MeetRoomBooking( room, dto.getResponsable(), dto.getFechaInicio(), dto.getFechaFin());
    }

    public static MeetRoomBookingResponseDTO toResponseDTO(MeetRoomBooking entity){
        MeetRoomBookingResponseDTO dto = new MeetRoomBookingResponseDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setSala(entity.getMeetRoom().getNombre());
        return dto;
    }

    public static DTO_MeetRoomBookingRequest toRequestDTO(DTO_MeetRoomBooking data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);

        DTO_MeetRoomBookingRequest dto = new DTO_MeetRoomBookingRequest();
        dto.setResponsable(data.getResponsable());
        dto.setSala(data.getSala());
        dto.setFechaInicio(sdf.parse(data.getFechaInicio()));
        dto.setFechaFin(sdf.parse(data.getFechaFin()));
        return dto;
    }

    private MeetRoomBookingDTOUtil(){
        throw new IllegalStateException("Utility class");
    }
}
