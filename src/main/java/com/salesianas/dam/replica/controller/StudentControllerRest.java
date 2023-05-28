package com.salesianas.dam.replica.controller;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.StudentRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.payload.request.EditRequest;
import com.salesianas.dam.replica.response.ReplicaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StudentControllerRest {

    ResponseEntity<ReplicaResponse<StudentRest>> studentDetails(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<StudentRest>> studentDetailsByUsername(String username) throws ReplicaException;

    ResponseEntity<ReplicaResponse<StudentRest>>  modifyStudent(StudentRest student, Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<StudentRest>>  editStudent(EditRequest student, Long id) throws ReplicaException;

    ResponseEntity deleteStudent(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<StudentRest>>  createStudent(StudentRest studentRest) throws ReplicaException;

    ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<StudentRest>>> listStudents(
            Pageable pageable
    ) throws ReplicaException;
}
