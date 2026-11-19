package com.test.meetroomBooking.util;

import com.test.meetroomBooking.model.dto.DTO_MeetRoom;
import com.test.meetroomBooking.model.dto.DTO_MeetRoomResponse;
import com.test.meetroomBooking.model.dto.DTO_UserAppResponse;
import com.test.meetroomBooking.model.dto.LoginRequest;
import com.test.meetroomBooking.model.persistence.MeetRoom;
import com.test.meetroomBooking.model.persistence.UserApp;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserAppDTOUtil {

    public static UserApp toEntity(LoginRequest dto){
        UserApp entity = new UserApp();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public static DTO_UserAppResponse toDTO(UserApp entity){
        return new DTO_UserAppResponse(entity.getId(), entity.getUsername(), entity.getPassword());
    }

    private UserAppDTOUtil(){
        throw new IllegalStateException("Utility class");
    }
}
