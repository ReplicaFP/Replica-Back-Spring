package com.salesianas.dam.replica.service;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.EmployeeRest;
import com.salesianas.dam.replica.dto.StudentRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.payload.request.EditRequest;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    EmployeeRest getEmployee(Long id) throws ReplicaException;

    EmployeeRest getEmployeeByUsername(String username) throws ReplicaException;

    CustomPagedResourceDTO<EmployeeRest> listEmployees(Pageable pageable) throws ReplicaException;

    EmployeeRest modifyEmployee(EmployeeRest employeeRest, Long id) throws ReplicaException;

    EmployeeRest editEmployee(EditRequest employee, Long id) throws ReplicaException;

    void deleteEmployee(Long id) throws ReplicaException;

    EmployeeRest createEmployee(EmployeeRest employeeRest) throws ReplicaException;
}
