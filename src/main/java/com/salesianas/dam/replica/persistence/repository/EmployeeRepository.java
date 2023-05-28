package com.salesianas.dam.replica.persistence.repository;

import com.salesianas.dam.replica.persistence.entity.EmployeeEntity;
import com.salesianas.dam.replica.persistence.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long> {
    Page<EmployeeEntity> findAll(Pageable page);

    Optional<EmployeeEntity> findByUsername(String username);
}
