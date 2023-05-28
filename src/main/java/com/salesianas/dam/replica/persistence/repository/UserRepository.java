package com.salesianas.dam.replica.persistence.repository;

import com.salesianas.dam.replica.persistence.entity.TeacherEntity;
import com.salesianas.dam.replica.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Override
    Page<UserEntity> findAll(Pageable page);
}
