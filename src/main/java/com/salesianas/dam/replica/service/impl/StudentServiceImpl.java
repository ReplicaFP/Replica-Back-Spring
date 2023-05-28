package com.salesianas.dam.replica.service.impl;

import com.salesianas.dam.replica.dto.CustomPagedResourceAssembler;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.StudentRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.exception.ReplicaNotFoundException;
import com.salesianas.dam.replica.mapper.StudentMapper;
import com.salesianas.dam.replica.payload.request.EditRequest;
import com.salesianas.dam.replica.persistence.entity.StudentEntity;
import com.salesianas.dam.replica.persistence.repository.StudentRepository;
import com.salesianas.dam.replica.service.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    CustomPagedResourceAssembler<StudentRest> customPagedResourceAssembler;

    @Override
    public StudentRest getStudent(Long id) throws ReplicaException {
        return studentRepository.findById(id)
                .map(student -> studentMapper.studentEntityToStudentRest(student)).orElseThrow( ()->new ReplicaNotFoundException(String.format("Student with ID: [%s] not found.", id), "404"));

    }

    @Override
    public StudentRest getStudentByUsername(String username) throws ReplicaException {
        return studentRepository.findByUsername(username)
                .map(student -> studentMapper.studentEntityToStudentRest(student)).orElseThrow( ()->new ReplicaNotFoundException(String.format("Student with Username: [%s] not found.", username), "404"));

    }

    @Override
    public CustomPagedResourceDTO<StudentRest> listStudents(@Parameter(hidden=true)Pageable pageable) throws ReplicaException {
        Page<StudentEntity> studentPage = studentRepository.findAll(pageable);
        Page<StudentRest> studentRestPage = studentPage.map(studentMapper::studentEntityToStudentRest);
        return  customPagedResourceAssembler.toModel(studentRestPage);

    }

    @Override
    public StudentRest modifyStudent(StudentRest studentRest, Long id) throws ReplicaException {
        return studentMapper.studentEntityToStudentRest(studentRepository.findById(id).map(studentSaved -> {
                    studentSaved.setId(id);
                    studentSaved.setUsername(studentRest.getUsername());
                    studentSaved.setName(studentRest.getName());
                    studentSaved.setLastName(studentRest.getLastName());
                    studentSaved.setLogin_user(studentRest.getLogin_user());

                    return studentRepository.save(studentSaved);
                }).orElseThrow(() -> new ReplicaNotFoundException(String.format("Student with ID: [%s] not found.", id), "404"))
        );
    }

    @Override
    public StudentRest editStudent(EditRequest student, Long id) throws ReplicaException {
        StudentEntity studentEntity= studentRepository.findById(id).orElseThrow(() -> new ReplicaNotFoundException(String.format("Student with ID: [%s] not found.", id), "404"));
        studentEntity.setUsername(student.getUsername());
        StudentEntity studentSaved=studentRepository.save(studentEntity);
        return studentMapper.studentEntityToStudentRest(studentSaved);
    }

    @Override
    public void deleteStudent(Long id) throws ReplicaException {
        StudentEntity studentEntity= studentRepository.findById(id).orElseThrow( ()->new ReplicaNotFoundException(String.format("Student with ID: [%s] not found.", id), "404"));
        studentRepository.delete(studentEntity);
    }

    @Override
    public StudentRest createStudent(StudentRest studentRest) throws ReplicaException {
        return studentMapper.studentEntityToStudentRest(studentRepository.save(studentMapper.studentRestToStudentEntity(studentRest)));
    }
}
