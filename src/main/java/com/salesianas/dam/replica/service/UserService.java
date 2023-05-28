package com.salesianas.dam.replica.service;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.TeacherRest;
import com.salesianas.dam.replica.dto.UserRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserRest getUser(Long id) throws ReplicaException;

    UserRest getUserByUsername(String username) throws ReplicaException;

    CustomPagedResourceDTO<UserRest> listUsers(Pageable pageable) throws ReplicaException;

    UserRest modifyUser(UserRest user, Long id) throws ReplicaException;

    void deleteUser(Long id) throws ReplicaException;

    void deleteUserByUsername(String username) throws ReplicaException;

    UserRest createUser(UserRest userRest) throws ReplicaException;
}
