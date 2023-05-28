package com.salesianas.dam.replica.service;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.FinalProjectRest;
import com.salesianas.dam.replica.dto.InternshipRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import org.springframework.data.domain.Pageable;

public interface InternshipService {

    InternshipRest getInternship(Long id) throws ReplicaException;

    CustomPagedResourceDTO<InternshipRest> listInternships(Pageable pageable) throws ReplicaException;

    InternshipRest modifyInternship(InternshipRest internshipRest, Long id) throws ReplicaException;

    void deleteInternship(Long id) throws ReplicaException;

    InternshipRest createInternship(InternshipRest internshipRest) throws ReplicaException;
}
