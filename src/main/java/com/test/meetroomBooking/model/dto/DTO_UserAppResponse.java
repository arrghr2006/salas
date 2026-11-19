package com.test.meetroomBooking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTO_UserAppResponse {

    private Integer id;

    private String userName;

    private String password;

}
