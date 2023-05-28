package com.salesianas.dam.replica.mapper;

import com.salesianas.dam.replica.dto.FinalProjectRest;
import com.salesianas.dam.replica.persistence.entity.FinalProjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FinalProjectMapper {
    FinalProjectRest finalProjectEntityToFinalProjectRest(FinalProjectEntity finalProjectEntity);

    FinalProjectEntity finalProjectRestToFinalProjectEntity(FinalProjectRest finalProjectRest);

}
