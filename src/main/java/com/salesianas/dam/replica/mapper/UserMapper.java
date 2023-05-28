package com.salesianas.dam.replica.mapper;

import com.salesianas.dam.replica.dto.UserRest;
import com.salesianas.dam.replica.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRest userEntityToUserRest(UserEntity userEntity);

    UserEntity userRestToUserEntity(UserRest userRest);
}
