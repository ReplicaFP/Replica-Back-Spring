package com.salesianas.dam.replica.controller;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.TeacherRest;
import com.salesianas.dam.replica.dto.UserRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.response.ReplicaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserControllerRest {
    ResponseEntity<ReplicaResponse<UserRest>> userDetails(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<UserRest>> userDetailsByUsername(String username) throws ReplicaException;

    ResponseEntity<ReplicaResponse<UserRest>>  modifyUser(UserRest user, Long id) throws ReplicaException;

    ResponseEntity deleteUser(Long id) throws ReplicaException;

    ResponseEntity deleteUserByUsername(String username) throws ReplicaException;

    ResponseEntity<ReplicaResponse<UserRest>>  createUser(UserRest userRest) throws ReplicaException;

    ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<UserRest>>> listUsers(
            Pageable pageable
    ) throws ReplicaException;
}
