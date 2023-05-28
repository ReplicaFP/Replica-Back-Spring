package com.salesianas.dam.replica.mapper;

import com.salesianas.dam.replica.dto.EmployeeRest;
import com.salesianas.dam.replica.persistence.entity.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeRest employeeEntityToEmployeeRest(EmployeeEntity employeeEntity);

    EmployeeEntity employeeRestToEmployeeEntity(EmployeeRest employeeRest);
}
