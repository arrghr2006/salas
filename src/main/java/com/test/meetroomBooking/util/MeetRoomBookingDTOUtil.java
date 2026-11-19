package com.test.meetroomBooking.util;

import com.test.meetroomBooking.model.dto.DTO_MeetRoomBooking;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBookingRequest;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBookingResponse;
import com.test.meetroomBooking.model.persistence.MeetRoom;
import com.test.meetroomBooking.model.persistence.MeetRoomBooking;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    public static DTO_MeetRoomBookingResponse toResponseDTO(MeetRoomBooking entity){
        DTO_MeetRoomBookingResponse dto = new DTO_MeetRoomBookingResponse();
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
