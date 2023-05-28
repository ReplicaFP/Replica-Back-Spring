package com.salesianas.dam.replica.mapper;

import com.salesianas.dam.replica.dto.TeacherRest;
import com.salesianas.dam.replica.persistence.entity.TeacherEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherRest teacherEntityToTeacherRest(TeacherEntity teacherEntity);

    TeacherEntity teacherRestToTeacherEntity(TeacherRest teacherRest);
}
