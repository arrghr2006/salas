package com.test.meetroomBooking.util;

import com.test.meetroomBooking.model.dto.DTO_MeetRoom;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomResponse;
import com.test.meetroomBooking.model.persistence.MeetRoom;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class MeetRoomDTOUtil {

    public static MeetRoom toEntity(DTO_MeetRoom dto){
        MeetRoom entity = new MeetRoom();
        entity.setNombre(dto.getNombre());

        return entity;
    }

    public static DTO_MeetRoom toDTO(MeetRoom entity){
        DTO_MeetRoom dto = new DTO_MeetRoom();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static DTO_MeetRoomResponse toResponseDTO(MeetRoom entity){
        return new DTO_MeetRoomResponse(entity.getId(), entity.getNombre());
    }

    private MeetRoomDTOUtil(){
        throw new IllegalStateException("Utility class");
    }
}
