package com.salesianas.dam.replica.controller.impl;

import com.salesianas.dam.replica.controller.EmployeeControllerRest;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.EmployeeRest;
import com.salesianas.dam.replica.dto.TeacherRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.payload.request.EditRequest;
import com.salesianas.dam.replica.response.ReplicaResponse;
import com.salesianas.dam.replica.response.ReplicaResponseStatus;
import com.salesianas.dam.replica.service.impl.EmployeeServiceImpl;
import com.salesianas.dam.replica.service.impl.TeacherServiceImpl;
import com.salesianas.dam.replica.utils.constant.RestConstantsUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Employee", description = "Employee rest")
@RequestMapping(value = RestConstantsUtils.API_VERSION_1 + RestConstantsUtils.RESOURCE_EMPLOYEES)
public class EmployeeControllerRestImpl implements EmployeeControllerRest {

    @Autowired
    private EmployeeServiceImpl employeeService;
    @Override
    @GetMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<EmployeeRest>> employeeDetails(@PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Employee successfully recovered")
                .data(employeeService.getEmployee(id))
                .build();

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping(value = RestConstantsUtils.RESOURCE_USERNAMES+RestConstantsUtils.RESOURCE_USERNAME, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<ReplicaResponse<EmployeeRest>> employeeDetailsByUsername(@PathVariable String  username) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Employee successfully recovered")
                .data(employeeService.getEmployeeByUsername(username))
                .build();

        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<EmployeeRest>> modifyEmployee(@RequestBody EmployeeRest employee, @PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Employee successfully updated")
                .data(employeeService.modifyEmployee(employee, id))
                .build();

        return ResponseEntity.accepted().body(response);
    }

    @Override
    @PatchMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<EmployeeRest>> editEmployee(@RequestBody EditRequest employee, @PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Employee successfully updated")
                .data(employeeService.editEmployee(employee, id))
                .build();

        return ResponseEntity.accepted().body(response);
    }

    @Override
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_ID)
    public ResponseEntity deleteEmployee(@PathVariable Long id) throws ReplicaException {
        employeeService.deleteEmployee(id);

        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Employee successfully deleted")
                .build();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ReplicaResponse<EmployeeRest>> createEmployee(@RequestBody EmployeeRest employeeRest) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Employee successfully created")
                .data(employeeService.createEmployee(employeeRest))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<EmployeeRest>>> listEmployees(@Parameter(hidden=true)Pageable pageable) throws ReplicaException {

            ReplicaResponse response = ReplicaResponse.builder()
                    .status(ReplicaResponseStatus.OK)
                    .message("Employees successfully recovered")
                    .data(employeeService.listEmployees(pageable))
                    .build();

            return ResponseEntity.ok(response);
    }
}
