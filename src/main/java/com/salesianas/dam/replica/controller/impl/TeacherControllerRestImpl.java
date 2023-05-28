package com.salesianas.dam.replica.controller.impl;

import com.salesianas.dam.replica.controller.TeacherControllerRest;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.StudentRest;
import com.salesianas.dam.replica.dto.TeacherRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.payload.request.EditRequest;
import com.salesianas.dam.replica.response.ReplicaResponse;
import com.salesianas.dam.replica.response.ReplicaResponseStatus;
import com.salesianas.dam.replica.service.impl.StudentServiceImpl;
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
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Teacher", description = "Teacher rest")
@RequestMapping(value = RestConstantsUtils.API_VERSION_1 + RestConstantsUtils.RESOURCE_TEACHERS)
public class TeacherControllerRestImpl implements TeacherControllerRest {

    @Autowired
    private TeacherServiceImpl teacherService;
    @Override
    @GetMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<TeacherRest>> teacherDetails(@PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Teacher successfully recovered")
                .data(teacherService.getTeacher(id))
                .build();

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping(value = RestConstantsUtils.RESOURCE_USERNAMES+RestConstantsUtils.RESOURCE_USERNAME, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<ReplicaResponse<TeacherRest>> teacherDetailsByUsername(@PathVariable String username) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Teacher successfully recovered")
                .data(teacherService.getTeacherByUsername(username))
                .build();

        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<TeacherRest>> modifyTeacher(@RequestBody TeacherRest teacher, @PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Teacher successfully updated")
                .data(teacherService.modifyTeacher(teacher, id))
                .build();

        return ResponseEntity.accepted().body(response);
    }

    @Override
    @PatchMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<TeacherRest>> editTeacher(@RequestBody EditRequest teacher, @PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Teacher successfully updated")
                .data(teacherService.editTeacher(teacher, id))
                .build();

        return ResponseEntity.accepted().body(response);
    }

    @Override
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_ID)
    public ResponseEntity deleteTeacher(@PathVariable Long id) throws ReplicaException {
        teacherService.deleteTeacher(id);

        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Teacher successfully deleted")
                .build();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ReplicaResponse<TeacherRest>> createTeacher(@RequestBody TeacherRest teacherRest) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Teacher successfully created")
                .data(teacherService.createTeacher(teacherRest))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<TeacherRest>>> listTeachers(@Parameter(hidden=true)Pageable pageable) throws ReplicaException {
            ReplicaResponse response = ReplicaResponse.builder()
                    .status(ReplicaResponseStatus.OK)
                    .message("Teachers successfully recovered")
                    .data(teacherService.listTeachers(pageable))
                    .build();

            return ResponseEntity.ok(response);
    }
}
