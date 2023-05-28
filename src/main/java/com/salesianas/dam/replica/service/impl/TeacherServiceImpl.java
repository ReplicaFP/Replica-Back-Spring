package com.salesianas.dam.replica.service.impl;

import com.salesianas.dam.replica.dto.CustomPagedResourceAssembler;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.StudentRest;
import com.salesianas.dam.replica.dto.TeacherRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.exception.ReplicaNotFoundException;
import com.salesianas.dam.replica.mapper.StudentMapper;
import com.salesianas.dam.replica.mapper.TeacherMapper;
import com.salesianas.dam.replica.payload.request.EditRequest;
import com.salesianas.dam.replica.persistence.entity.StudentEntity;
import com.salesianas.dam.replica.persistence.entity.TeacherEntity;
import com.salesianas.dam.replica.persistence.repository.StudentRepository;
import com.salesianas.dam.replica.persistence.repository.TeacherRepository;
import com.salesianas.dam.replica.service.TeacherService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    CustomPagedResourceAssembler<TeacherRest> customPagedResourceAssembler;
    @Override
    public TeacherRest getTeacher(Long id) throws ReplicaException {
        return teacherRepository.findById(id)
                .map(teacher -> teacherMapper.teacherEntityToTeacherRest(teacher)).orElseThrow( ()->new ReplicaNotFoundException(String.format("Teacher with ID: [%s] not found.", id), "404"));

    }

    @Override
    public TeacherRest getTeacherByUsername(String username) throws ReplicaException {
        return teacherRepository.findByUsername(username)
                .map(teacher -> teacherMapper.teacherEntityToTeacherRest(teacher)).orElseThrow( ()->new ReplicaNotFoundException(String.format("Teacher with Username: [%s] not found.", username), "404"));

    }

    @Override
    public CustomPagedResourceDTO<TeacherRest> listTeachers(@Parameter(hidden=true)Pageable pageable) throws ReplicaException {
        Page<TeacherEntity> teacherPage = teacherRepository.findAll(pageable);
        Page<TeacherRest> teacherRestPage = teacherPage.map(teacherMapper::teacherEntityToTeacherRest);
        return  customPagedResourceAssembler.toModel(teacherRestPage);
    }

    @Override
    public TeacherRest modifyTeacher(TeacherRest teacherRest, Long id) throws ReplicaException {
        return teacherMapper.teacherEntityToTeacherRest(teacherRepository.findById(id).map(teacherSaved -> {

                    teacherSaved.setId(id);
                    teacherSaved.setUsername(teacherRest.getUsername());
                    teacherSaved.setName(teacherRest.getName());
                    teacherSaved.setLastName(teacherRest.getLastName());
                    teacherSaved.setLogin_user(teacherRest.getLogin_user());
                    return teacherRepository.save(teacherSaved);
                }).orElseThrow(() -> new ReplicaNotFoundException(String.format("Teacher with ID: [%s] not found.", id), "404"))
        );
    }

    @Override
    public TeacherRest editTeacher(EditRequest teacher, Long id) throws ReplicaException {
        TeacherEntity teacherEntity= teacherRepository.findById(id).orElseThrow(() -> new ReplicaNotFoundException(String.format("Teacher with ID: [%s] not found.", id), "404"));
        teacherEntity.setUsername(teacher.getUsername());
        TeacherEntity studentSaved=teacherRepository.save(teacherEntity);
        return teacherMapper.teacherEntityToTeacherRest(studentSaved);
    }

    @Override
    public void deleteTeacher(Long id) throws ReplicaException {
        TeacherEntity teacherEntity= teacherRepository.findById(id).orElseThrow( ()->new ReplicaNotFoundException(String.format("Teacher with ID: [%s] not found.", id), "404"));
        teacherRepository.delete(teacherEntity);
    }

    @Override
    public TeacherRest createTeacher(TeacherRest teacherRest) throws ReplicaException {
        return teacherMapper.teacherEntityToTeacherRest(teacherRepository.save(teacherMapper.teacherRestToTeacherEntity(teacherRest)));
    }
}
