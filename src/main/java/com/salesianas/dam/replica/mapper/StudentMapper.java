package com.salesianas.dam.replica.mapper;

import com.salesianas.dam.replica.dto.StudentRest;
import com.salesianas.dam.replica.persistence.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentRest studentEntityToStudentRest(StudentEntity studentEntity);

    StudentEntity studentRestToStudentEntity(StudentRest studentRest);
}
