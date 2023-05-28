package com.salesianas.dam.replica.service;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.StudentRest;
import com.salesianas.dam.replica.dto.TeacherRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.payload.request.EditRequest;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
    TeacherRest getTeacher(Long id) throws ReplicaException;

    TeacherRest getTeacherByUsername(String username) throws ReplicaException;

    CustomPagedResourceDTO<TeacherRest> listTeachers(Pageable pageable) throws ReplicaException;

    TeacherRest modifyTeacher(TeacherRest teacher, Long id) throws ReplicaException;

    TeacherRest editTeacher(EditRequest teacher, Long id) throws ReplicaException;

    void deleteTeacher(Long id) throws ReplicaException;

    TeacherRest createTeacher(TeacherRest teacherRest) throws ReplicaException;

}
