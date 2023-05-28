package com.salesianas.dam.replica.controller;
import javax.validation.Valid;

import com.salesianas.dam.replica.payload.request.LoginRequest;
import com.salesianas.dam.replica.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
public interface AuthControllerRest {


        ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest);


        ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest);
}
