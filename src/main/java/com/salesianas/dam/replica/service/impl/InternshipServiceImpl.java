package com.salesianas.dam.replica.service.impl;

import com.salesianas.dam.replica.dto.CustomPagedResourceAssembler;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.FinalProjectRest;
import com.salesianas.dam.replica.dto.InternshipRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.exception.ReplicaNotFoundException;
import com.salesianas.dam.replica.mapper.FinalProjectMapper;
import com.salesianas.dam.replica.mapper.InternshipMapper;
import com.salesianas.dam.replica.persistence.entity.FinalProjectEntity;
import com.salesianas.dam.replica.persistence.entity.InternshipEntity;
import com.salesianas.dam.replica.persistence.repository.FinalProjectRepository;
import com.salesianas.dam.replica.persistence.repository.InternshipRepository;
import com.salesianas.dam.replica.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InternshipServiceImpl implements InternshipService {

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private InternshipMapper internshipMapper;

    @Autowired
    CustomPagedResourceAssembler<InternshipRest> customPagedResourceAssembler;
    @Override
    public InternshipRest getInternship(Long id) throws ReplicaException {
        return internshipRepository.findById(id)
                .map(internship -> internshipMapper.internshipEntityToInternshipRest(internship)).orElseThrow( ()->new ReplicaNotFoundException(String.format("Internship with ID: [%s] not found.", id), "404"));

    }

    @Override
    public CustomPagedResourceDTO<InternshipRest> listInternships(Pageable pageable) throws ReplicaException {
        Page<InternshipEntity> internshipPage = internshipRepository.findAll(pageable);
        Page<InternshipRest> internshipRestPage = internshipPage.map(internshipMapper::internshipEntityToInternshipRest);
        return  customPagedResourceAssembler.toModel(internshipRestPage);
    }

    @Override
    public InternshipRest modifyInternship(InternshipRest internshipRest, Long id) throws ReplicaException {
        return internshipMapper.internshipEntityToInternshipRest(internshipRepository.findById(id).map(internshipSaved -> {
                    internshipSaved = internshipMapper.internshipRestToInternshipEntity(internshipRest);
                    internshipSaved.setId(id);
                    return internshipRepository.save(internshipSaved);
                }).orElseThrow(() -> new ReplicaNotFoundException(String.format("Internship with ID: [%s] not found.", id), "404"))
        );
    }

    @Override
    public void deleteInternship(Long id) throws ReplicaException {
        InternshipEntity internshipEntity= internshipRepository.findById(id).orElseThrow( ()->new ReplicaNotFoundException(String.format("Internship with ID: [%s] not found.", id), "404"));
        internshipRepository.delete(internshipEntity);
    }

    @Override
    public InternshipRest createInternship(InternshipRest internshipRest) throws ReplicaException {
        return internshipMapper.internshipEntityToInternshipRest(internshipRepository.save(internshipMapper.internshipRestToInternshipEntity(internshipRest)));
    }
}
