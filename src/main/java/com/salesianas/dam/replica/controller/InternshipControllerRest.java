package com.salesianas.dam.replica.controller;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.InternshipRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.response.ReplicaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InternshipControllerRest {

    ResponseEntity<ReplicaResponse<InternshipRest>> internshipDetails(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<InternshipRest>>  modifyInternship(InternshipRest internship, Long id) throws ReplicaException;

    ResponseEntity deleteInternship(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<InternshipRest>>  createInternship(InternshipRest internshipRest) throws ReplicaException;

    ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<InternshipRest>>> listInternships(
            Pageable pageable
    ) throws ReplicaException;
}
