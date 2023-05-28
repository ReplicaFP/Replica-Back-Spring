package com.salesianas.dam.replica.service.impl;

import com.salesianas.dam.replica.dto.CustomPagedResourceAssembler;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.RoleRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.mapper.RoleMapper;
import com.salesianas.dam.replica.persistence.entity.RoleEntity;
import com.salesianas.dam.replica.persistence.repository.RoleRepository;
import com.salesianas.dam.replica.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;
    CustomPagedResourceAssembler<RoleRest> customPagedResourceAssembler;

    public RoleServiceImpl(
            RoleRepository roleRepository,
            RoleMapper roleMapper,
            CustomPagedResourceAssembler<RoleRest> customPagedResourceAssembler) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.customPagedResourceAssembler = customPagedResourceAssembler;
    }
    @Transactional
    @Override
    public CustomPagedResourceDTO<RoleEntity> roleList(Pageable pageable) throws ReplicaException {
        Page<RoleEntity> rolePage = roleRepository.findAll(pageable);
        Page<RoleRest> roleRestPage = rolePage.map(roleMapper::roleEntityToRoleRest);
        CustomPagedResourceDTO<RoleEntity> response = customPagedResourceAssembler.toModel(roleRestPage);
        return response;
    }
}
