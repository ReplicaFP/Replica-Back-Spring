package com.salesianas.dam.replica.service;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.EmployeeRest;
import com.salesianas.dam.replica.dto.FinalProjectRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import org.springframework.data.domain.Pageable;

public interface FinalProjectService {

    FinalProjectRest getFinalProject(Long id) throws ReplicaException;

    CustomPagedResourceDTO<FinalProjectRest> listFinalProject(Pageable pageable) throws ReplicaException;

    FinalProjectRest modifyFinalProject(FinalProjectRest finalProjectRest, Long id) throws ReplicaException;

    void deleteFinalProject(Long id) throws ReplicaException;

    FinalProjectRest createFinalProject(FinalProjectRest finalProjectRest) throws ReplicaException;
}
