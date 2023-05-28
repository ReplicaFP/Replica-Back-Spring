package com.salesianas.dam.replica.mapper;

import com.salesianas.dam.replica.dto.InternshipRest;
import com.salesianas.dam.replica.persistence.entity.InternshipEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternshipMapper {

    InternshipRest internshipEntityToInternshipRest(InternshipEntity internshipEntity);

    InternshipEntity internshipRestToInternshipEntity(InternshipRest internshipRest);
}
