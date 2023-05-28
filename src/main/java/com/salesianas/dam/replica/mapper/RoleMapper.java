package com.salesianas.dam.replica.mapper;


import com.salesianas.dam.replica.dto.RoleRest;
import com.salesianas.dam.replica.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleEntity roleRestToRoleEntity(RoleRest roleRest);


    RoleRest roleEntityToRoleRest(RoleEntity roleEntity);
}
