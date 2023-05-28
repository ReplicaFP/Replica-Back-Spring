package com.salesianas.dam.replica.service;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.persistence.entity.RoleEntity;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    CustomPagedResourceDTO<RoleEntity> roleList(Pageable pageable) throws ReplicaException;
}
