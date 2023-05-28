package com.salesianas.dam.replica.controller;

import com.salesianas.dam.replica.dto.RoleRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.response.ReplicaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

public interface RoleControllerRest {

    ResponseEntity<ReplicaResponse<PagedModel<EntityModel<RoleRest>>>> roleList(
            Pageable pageable
    ) throws ReplicaException;
}
