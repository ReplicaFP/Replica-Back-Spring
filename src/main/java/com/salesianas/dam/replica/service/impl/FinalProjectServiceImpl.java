package com.salesianas.dam.replica.service.impl;

import com.salesianas.dam.replica.dto.CustomPagedResourceAssembler;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.EmployeeRest;
import com.salesianas.dam.replica.dto.FinalProjectRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.exception.ReplicaNotFoundException;
import com.salesianas.dam.replica.mapper.EmployeeMapper;
import com.salesianas.dam.replica.mapper.FinalProjectMapper;
import com.salesianas.dam.replica.persistence.entity.EmployeeEntity;
import com.salesianas.dam.replica.persistence.entity.FinalProjectEntity;
import com.salesianas.dam.replica.persistence.repository.EmployeeRepository;
import com.salesianas.dam.replica.persistence.repository.FinalProjectRepository;
import com.salesianas.dam.replica.service.FinalProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FinalProjectServiceImpl implements FinalProjectService {

    @Autowired
    private FinalProjectRepository finalProjectRepository;

    @Autowired
    private FinalProjectMapper finalProjectMapper;

    @Autowired
    CustomPagedResourceAssembler<FinalProjectRest> customPagedResourceAssembler;
    @Override
    public FinalProjectRest getFinalProject(Long id) throws ReplicaException {
        return finalProjectRepository.findById(id)
                .map(finalProject -> finalProjectMapper.finalProjectEntityToFinalProjectRest(finalProject)).orElseThrow( ()->new ReplicaNotFoundException(String.format("FinalProject with ID: [%s] not found.", id), "404"));

    }

    @Override
    public CustomPagedResourceDTO<FinalProjectRest> listFinalProject(Pageable pageable) throws ReplicaException {
        Page<FinalProjectEntity> finalProjectPage = finalProjectRepository.findAll(pageable);
        Page<FinalProjectRest> finalProjectRestPage = finalProjectPage.map(finalProjectMapper::finalProjectEntityToFinalProjectRest);
        return  customPagedResourceAssembler.toModel(finalProjectRestPage);
    }

    @Override
    public FinalProjectRest modifyFinalProject(FinalProjectRest finalProjectRest, Long id) throws ReplicaException {
        return finalProjectMapper.finalProjectEntityToFinalProjectRest(finalProjectRepository.findById(id).map(finalProjectSaved -> {
                    finalProjectSaved = finalProjectMapper.finalProjectRestToFinalProjectEntity(finalProjectRest);
                    finalProjectSaved.setId(id);
                    return finalProjectRepository.save(finalProjectSaved);
                }).orElseThrow(() -> new ReplicaNotFoundException(String.format("FinalProject with ID: [%s] not found.", id), "404"))
        );
    }

    @Override
    public void deleteFinalProject(Long id) throws ReplicaException {
        FinalProjectEntity finalProjectEntity= finalProjectRepository.findById(id).orElseThrow( ()->new ReplicaNotFoundException(String.format("FinalProject with ID: [%s] not found.", id), "404"));
        finalProjectRepository.delete(finalProjectEntity);
    }

    @Override
    public FinalProjectRest createFinalProject(FinalProjectRest finalProjectRest) throws ReplicaException {
        return finalProjectMapper.finalProjectEntityToFinalProjectRest(finalProjectRepository.save(finalProjectMapper.finalProjectRestToFinalProjectEntity(finalProjectRest)));
    }
}
