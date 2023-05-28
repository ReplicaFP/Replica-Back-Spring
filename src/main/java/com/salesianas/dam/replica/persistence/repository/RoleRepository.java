package com.salesianas.dam.replica.persistence.repository;

import com.salesianas.dam.replica.dto.ERole;
import com.salesianas.dam.replica.persistence.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {

    @Override
    Page<RoleEntity> findAll(Pageable page);

    Optional<RoleEntity> findByName(ERole name);

}
