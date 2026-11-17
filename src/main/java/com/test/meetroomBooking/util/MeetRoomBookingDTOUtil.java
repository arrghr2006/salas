package com.test.meetroomBooking.util;

import com.test.meetroomBooking.model.dto.DTO_MeetRoom;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomBooking;
import com.test.meetroomBooking.model.persistence.MeetRoom;
import com.test.meetroomBooking.model.persistence.MeetRoomBooking;
import com.test.meetroomBooking.repository.MeetRoomRepository;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class MeetRoomBookingDTOUtil {

    private final MeetRoomRepository meetRoomRepository;

     public static DTO_MeetRoom toDTO(MeetRoom entity){
        DTO_MeetRoom dto = new DTO_MeetRoom();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
    private MeetRoomBookingDTOUtil(){
        throw new IllegalStateException("Utility class");
    }
}
