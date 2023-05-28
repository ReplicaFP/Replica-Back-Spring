package com.salesianas.dam.replica.persistence.repository;


import com.salesianas.dam.replica.persistence.entity.FinalProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalProjectRepository extends PagingAndSortingRepository<FinalProjectEntity, Long> {
    Page<FinalProjectEntity> findAll(Pageable page);

}
