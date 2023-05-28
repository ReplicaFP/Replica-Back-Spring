package com.salesianas.dam.replica.controller.impl;

import com.salesianas.dam.replica.controller.UserControllerRest;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.UserRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.response.ReplicaResponse;
import com.salesianas.dam.replica.response.ReplicaResponseStatus;
import com.salesianas.dam.replica.service.impl.TeacherServiceImpl;
import com.salesianas.dam.replica.service.impl.UserServiceImpl;
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
@Tag(name = "User", description = "User rest")
@RequestMapping(value = RestConstantsUtils.API_VERSION_1 + RestConstantsUtils.RESOURCE_USERS)
public class UserControllerRestImpl implements UserControllerRest {

    @Autowired
    private UserServiceImpl userService;
    @Override
    @GetMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<UserRest>> userDetails(@PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("User successfully recovered")
                .data(userService.getUser(id))
                .build();

        return ResponseEntity.ok(response);
    }

    @Override
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = RestConstantsUtils.RESOURCE_USERNAMES+ RestConstantsUtils.RESOURCE_USERNAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<UserRest>> userDetailsByUsername(@PathVariable String username) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("User successfully recovered")
                .data(userService.getUserByUsername(username))
                .build();

        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<UserRest>> modifyUser(@RequestBody UserRest user, @PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("User successfully updated")
                .data(userService.modifyUser(user, id))
                .build();

        return ResponseEntity.accepted().body(response);
    }

    @Override
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_ID)
    public ResponseEntity deleteUser(@PathVariable Long id) throws ReplicaException {
        userService.deleteUser(id);

        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("User successfully deleted")
                .build();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @Override
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_USERNAMES+RestConstantsUtils.RESOURCE_USERNAME)
    public ResponseEntity deleteUserByUsername(@PathVariable String username) throws ReplicaException {
        userService.deleteUserByUsername(username);

        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("User successfully deleted")
                .build();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ReplicaResponse<UserRest>> createUser(@RequestBody UserRest userRest) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("User successfully created")
                .data(userService.createUser(userRest))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<UserRest>>> listUsers(@Parameter(hidden=true)Pageable pageable) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Users successfully recovered")
                .data(userService.listUsers(pageable))
                .build();

        return ResponseEntity.ok(response);
    }
}
