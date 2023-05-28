package com.salesianas.dam.replica.service.impl;

import com.salesianas.dam.replica.dto.CustomPagedResourceAssembler;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.TeacherRest;
import com.salesianas.dam.replica.dto.UserRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.exception.ReplicaNotFoundException;
import com.salesianas.dam.replica.mapper.TeacherMapper;
import com.salesianas.dam.replica.mapper.UserMapper;
import com.salesianas.dam.replica.persistence.entity.TeacherEntity;
import com.salesianas.dam.replica.persistence.entity.UserEntity;
import com.salesianas.dam.replica.persistence.repository.TeacherRepository;
import com.salesianas.dam.replica.persistence.repository.UserRepository;
import com.salesianas.dam.replica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    CustomPagedResourceAssembler<UserRest> customPagedResourceAssembler;
    @Override
    public UserRest getUser(Long id) throws ReplicaException {
        return userRepository.findById(id)
                .map(teacher -> userMapper.userEntityToUserRest(teacher)).orElseThrow( ()->new ReplicaNotFoundException(String.format("User with ID: [%s] not found.", id), "404"));
    }

    @Override
    public UserRest getUserByUsername(String username) throws ReplicaException {
        return userRepository.findByUsername(username)
                .map(teacher -> userMapper.userEntityToUserRest(teacher)).orElseThrow( ()->new ReplicaNotFoundException(String.format("User with Username: [%s] not found.", username), "404"));

    }

    @Override
    public CustomPagedResourceDTO<UserRest> listUsers(Pageable pageable) throws ReplicaException {
        Page<UserEntity> userPage = userRepository.findAll(pageable);
        Page<UserRest> userRestPage = userPage.map(userMapper::userEntityToUserRest);
        return  customPagedResourceAssembler.toModel(userRestPage);
    }

    @Override
    public UserRest modifyUser(UserRest user, Long id) throws ReplicaException {

        return userMapper.userEntityToUserRest(userRepository.findById(id).map(userSaved -> {
                    userSaved = userMapper.userRestToUserEntity(user);
                    userSaved.setPassword(encoder.encode(user.getPassword()));
                    userSaved.setId(id);
                    return userRepository.save(userSaved);
                }).orElseThrow(() -> new ReplicaNotFoundException(String.format("User with ID: [%s] not found.", id), "404"))
        );
    }

    @Override
    public void deleteUser(Long id) throws ReplicaException {
        UserEntity userEntity= userRepository.findById(id).orElseThrow( ()->new ReplicaNotFoundException(String.format("User with ID: [%s] not found.", id), "404"));
        userRepository.delete(userEntity);
    }

    @Override
    public void deleteUserByUsername(String username) throws ReplicaException {
        UserEntity userEntity= userRepository.findByUsername(username).orElseThrow( ()->new ReplicaNotFoundException(String.format("User with Username: [%s] not found.", username), "404"));
        userRepository.delete(userEntity);
    }

    @Override
    public UserRest createUser(UserRest userRest) throws ReplicaException {
        return userMapper.userEntityToUserRest(userRepository.save(userMapper.userRestToUserEntity(userRest)));
    }
}
