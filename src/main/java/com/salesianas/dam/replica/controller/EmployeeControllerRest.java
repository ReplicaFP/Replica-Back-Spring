package com.salesianas.dam.replica.controller;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.EmployeeRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.payload.request.EditRequest;
import com.salesianas.dam.replica.response.ReplicaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EmployeeControllerRest {
    ResponseEntity<ReplicaResponse<EmployeeRest>> employeeDetails(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<EmployeeRest>> employeeDetailsByUsername(String username) throws ReplicaException;

    ResponseEntity<ReplicaResponse<EmployeeRest>>  modifyEmployee(EmployeeRest employee, Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<EmployeeRest>>  editEmployee(EditRequest employee, Long id) throws ReplicaException;

    ResponseEntity deleteEmployee(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<EmployeeRest>>  createEmployee(EmployeeRest employeeRest) throws ReplicaException;

    ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<EmployeeRest>>> listEmployees(
            Pageable pageable
    ) throws ReplicaException;
}
