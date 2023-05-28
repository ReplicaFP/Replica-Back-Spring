package com.salesianas.dam.replica.controller;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.StudentRest;
import com.salesianas.dam.replica.dto.TeacherRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.payload.request.EditRequest;
import com.salesianas.dam.replica.response.ReplicaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TeacherControllerRest {

    ResponseEntity<ReplicaResponse<TeacherRest>> teacherDetails(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<TeacherRest>> teacherDetailsByUsername(String username) throws ReplicaException;

    ResponseEntity<ReplicaResponse<TeacherRest>>  modifyTeacher(TeacherRest teacher, Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<TeacherRest>>  editTeacher(EditRequest teacher, Long id) throws ReplicaException;

    ResponseEntity deleteTeacher(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<TeacherRest>>  createTeacher(TeacherRest teacherRest) throws ReplicaException;

    ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<TeacherRest>>> listTeachers(
            Pageable pageable
    ) throws ReplicaException;
}
