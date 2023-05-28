package com.salesianas.dam.replica.controller;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.EmployeeRest;
import com.salesianas.dam.replica.dto.FinalProjectRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.response.ReplicaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FinalProjectControllerRest {

    ResponseEntity<ReplicaResponse<FinalProjectRest>> finalProjectDetails(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<FinalProjectRest>>  modifyFinalProject(FinalProjectRest finalProject, Long id) throws ReplicaException;

    ResponseEntity deleteFinalProject(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<FinalProjectRest>>  createFinalProject(FinalProjectRest finalProjectRest) throws ReplicaException;

    ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<FinalProjectRest>>> listFinalProjects(
            Pageable pageable
    ) throws ReplicaException;
}
