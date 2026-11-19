package com.test.meetroomBooking.controller;

import com.test.meetroomBooking.model.dto.DTO_UserAppResponse;
import com.test.meetroomBooking.model.dto.JwtResponse;
import com.test.meetroomBooking.model.dto.LoginRequest;
import com.test.meetroomBooking.payload.GenericResponse;
import com.test.meetroomBooking.payload.MessageResponse;
import com.test.meetroomBooking.security.jwt.JwtUtils;
import com.test.meetroomBooking.service.UserDetailsImpl;
import com.test.meetroomBooking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<GenericResponse> authenticateUser(@RequestBody LoginRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtUtils.generateToken(authRequest.getUsername());
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            return new ResponseEntity<>(GenericResponse.builder().status(HttpStatus.OK).object(
                    new JwtResponse(token, userDetails.getId(), userDetails.getUsername())
            ).message("Autenticación exitosa").build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(GenericResponse.builder().status(HttpStatus.NOT_FOUND).object(null).message("Autenticación fallida.").build(),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody LoginRequest registerRequest) {
        DTO_UserAppResponse resultado = userService.create(registerRequest);
        return new ResponseEntity<>( MessageResponse.builder().status(HttpStatus.OK).object(resultado).message(null).build(), HttpStatus.CREATED);
   }
}
