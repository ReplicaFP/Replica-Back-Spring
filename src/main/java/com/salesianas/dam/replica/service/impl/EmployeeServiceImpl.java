package com.salesianas.dam.replica.service.impl;

import com.salesianas.dam.replica.dto.CustomPagedResourceAssembler;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.EmployeeRest;
import com.salesianas.dam.replica.dto.StudentRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.exception.ReplicaNotFoundException;
import com.salesianas.dam.replica.mapper.EmployeeMapper;
import com.salesianas.dam.replica.mapper.StudentMapper;
import com.salesianas.dam.replica.payload.request.EditRequest;
import com.salesianas.dam.replica.persistence.entity.EmployeeEntity;
import com.salesianas.dam.replica.persistence.entity.StudentEntity;
import com.salesianas.dam.replica.persistence.repository.EmployeeRepository;
import com.salesianas.dam.replica.persistence.repository.StudentRepository;
import com.salesianas.dam.replica.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    CustomPagedResourceAssembler<EmployeeRest> customPagedResourceAssembler;
    @Override
    public EmployeeRest getEmployee(Long id) throws ReplicaException {
        return employeeRepository.findById(id)
                .map(employee -> employeeMapper.employeeEntityToEmployeeRest(employee)).orElseThrow( ()->new ReplicaNotFoundException(String.format("Employee with ID: [%s] not found.", id), "404"));

    }

    @Override
    public EmployeeRest getEmployeeByUsername(String username) throws ReplicaException {
        return employeeRepository.findByUsername(username)
                .map(employee -> employeeMapper.employeeEntityToEmployeeRest(employee)).orElseThrow( ()->new ReplicaNotFoundException(String.format("Employee with Username: [%s] not found.", username), "404"));

    }

    @Override
    public CustomPagedResourceDTO<EmployeeRest> listEmployees(Pageable pageable) throws ReplicaException {
        Page<EmployeeEntity> employeePage = employeeRepository.findAll(pageable);
        Page<EmployeeRest> employeeRestPage = employeePage.map(employeeMapper::employeeEntityToEmployeeRest);
        return  customPagedResourceAssembler.toModel(employeeRestPage);
    }

    @Override
    public EmployeeRest modifyEmployee(EmployeeRest employeeRest, Long id) throws ReplicaException {
        return employeeMapper.employeeEntityToEmployeeRest(employeeRepository.findById(id).map(employeeSaved -> {

                    employeeSaved.setId(id);
                    employeeSaved.setUsername(employeeRest.getUsername());
                    employeeSaved.setName(employeeRest.getName());
                    employeeSaved.setLastName(employeeRest.getLastName());
                    employeeSaved.setLogin_user(employeeRest.getLogin_user());
                    return employeeRepository.save(employeeSaved);
                }).orElseThrow(() -> new ReplicaNotFoundException(String.format("Employee with ID: [%s] not found.", id), "404"))
        );
    }

    @Override
    public EmployeeRest editEmployee(EditRequest employee, Long id) throws ReplicaException {
        EmployeeEntity employeeEntity= employeeRepository.findById(id).orElseThrow(() -> new ReplicaNotFoundException(String.format("Employee with ID: [%s] not found.", id), "404"));
        employeeEntity.setUsername(employee.getUsername());
        EmployeeEntity employeeSaved=employeeRepository.save(employeeEntity);
        return employeeMapper.employeeEntityToEmployeeRest(employeeSaved);
    }

    @Override
    public void deleteEmployee(Long id) throws ReplicaException {
        EmployeeEntity employeeEntity= employeeRepository.findById(id).orElseThrow( ()->new ReplicaNotFoundException(String.format("Employee with ID: [%s] not found.", id), "404"));
        employeeRepository.delete(employeeEntity);
    }

    @Override
    public EmployeeRest createEmployee(EmployeeRest employeeRest) throws ReplicaException {
        return employeeMapper.employeeEntityToEmployeeRest(employeeRepository.save(employeeMapper.employeeRestToEmployeeEntity(employeeRest)));
    }
}
