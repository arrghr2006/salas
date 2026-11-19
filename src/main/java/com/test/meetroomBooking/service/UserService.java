package com.test.meetroomBooking.service;

import com.test.meetroomBooking.exception.ErrorCode;
import com.test.meetroomBooking.exception.ErrorMessage;
import com.test.meetroomBooking.exception.RequestException;
import com.test.meetroomBooking.model.dto.DTO_UserAppResponse;
import com.test.meetroomBooking.model.dto.LoginRequest;
import com.test.meetroomBooking.model.persistence.UserApp;
import com.test.meetroomBooking.repository.UserAppRepository;
import com.test.meetroomBooking.util.UserAppDTOUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserServiceInterface {

    private final UserAppRepository userRepository;
    private final PasswordEncoder encoder;
    private static final String ADMIN= "admin";

    public void initialCharge() {

        if ((this.userRepository.count() == 0) ||
            (!Boolean.TRUE.equals(this.userRepository.existsUserAppByUsername(ADMIN)))) {
                UserApp user = new UserApp( ADMIN, encoder.encode(ADMIN) );
                userRepository.save(user);
            }
    }

    public DTO_UserAppResponse create(LoginRequest data){
        if (userRepository.existsUserAppByUsername(data.getUsername())) {
            throw new RequestException( ErrorCode.DUPLICATED_USER, ErrorMessage.DUPLICATED_USER,
                    ErrorMessage.DUPLICATED_USER, HttpStatus.CONFLICT.name());
        }
        data.setPassword( encoder.encode(data.getPassword()));
        try {
            return UserAppDTOUtil.toDTO( userRepository.save(UserAppDTOUtil.toEntity(data)) );
        }catch (IllegalArgumentException exc){
            throw new RequestException( ErrorCode.BAD_REQUEST, ErrorMessage.VALIDATION_PARAMS,
                    ErrorMessage.VALIDATION_PARAMS, HttpStatus.BAD_REQUEST.name());
        }
    }
}
