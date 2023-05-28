package com.salesianas.dam.replica.controller.impl;

import com.salesianas.dam.replica.controller.RoleControllerRest;
import com.salesianas.dam.replica.dto.RoleRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.response.ReplicaResponse;
import com.salesianas.dam.replica.response.ReplicaResponseStatus;
import com.salesianas.dam.replica.service.impl.RoleServiceImpl;
import com.salesianas.dam.replica.utils.constant.RestConstantsUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Role", description = "Role rest")
@RequestMapping(value = RestConstantsUtils.API_VERSION_1 + RestConstantsUtils.RESOURCE_ROLES)
public class RoleControllerRestImpl implements RoleControllerRest {

    @Autowired
    private RoleServiceImpl roleServiceImpl;


    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasAnyAuthority('administrador')")
    public ResponseEntity<ReplicaResponse<PagedModel<EntityModel<RoleRest>>>> roleList(
            @Parameter(hidden = true) Pageable pageable
    ) throws ReplicaException {

        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Roles successfully recovered")
                .data(roleServiceImpl.roleList(pageable))
                .build();

        return ResponseEntity.ok(response);
    }
}
