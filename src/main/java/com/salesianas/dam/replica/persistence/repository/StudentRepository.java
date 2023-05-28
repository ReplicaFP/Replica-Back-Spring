package com.salesianas.dam.replica.persistence.repository;


import com.salesianas.dam.replica.persistence.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Long> {
    @Override
    Page<StudentEntity> findAll(Pageable page);

    Optional<StudentEntity> findByUsername(String username);
}
